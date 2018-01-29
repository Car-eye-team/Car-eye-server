/*
 *  Copyright 2004 Clinton Begin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.ibatis.sqlmap.engine.execution;


import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import com.ibatis.sqlmap.engine.execution.BatchException;
import com.ibatis.sqlmap.engine.execution.BatchResult;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMap;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMapping;
import com.ibatis.sqlmap.engine.mapping.result.ResultMap;
import com.ibatis.sqlmap.engine.mapping.result.ResultObjectFactoryUtil;
import com.ibatis.sqlmap.engine.mapping.statement.DefaultRowHandler;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.ErrorContext;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;

/**
 * Class responsible for executing the SQL
 */
@SuppressWarnings("unchecked")
public class SqlExecutor {
	
	private static final Logger log = Logger.getLogger(SqlExecutor.class);

  //
  // Constants
  //
  /**
   * Constant to let us know not to skip anything
   */
  public static final int NO_SKIPPED_RESULTS = 0;
  /**
   * Constant to let us know to include all records
   */
  public static final int NO_MAXIMUM_RESULTS = -999999;

  //
  // Public Methods
  //

  /**
   * Execute an update
   *
   * @param statementScope    - the request scope
   * @param conn       - the database connection
   * @param sql        - the sql statement to execute
   * @param parameters - the parameters for the sql statement
   * @return - the number of records changed
   * @throws SQLException - if the update fails
   */
  public int executeUpdate(StatementScope statementScope, Connection conn, String sql, Object[] parameters) throws SQLException {
    ErrorContext errorContext = statementScope.getErrorContext();
    errorContext.setActivity("executing update");
    errorContext.setObjectId(sql);
    PreparedStatement ps = null;
    setupResultObjectFactory(statementScope);
    int rows = 0;
    try {
      errorContext.setMoreInfo("Check the SQL Statement (preparation failed).");
      ps = prepareStatement(statementScope.getSession(), conn, sql);
      setStatementTimeout(statementScope.getStatement(), ps);
      errorContext.setMoreInfo("Check the parameters (set parameters failed).");
      statementScope.getParameterMap().setParameters(statementScope, ps, parameters);
      errorContext.setMoreInfo("Check the statement (update failed).");
      //扩展点1，用于扩展增删改操作的拼接sql功能，便于调试
      //打印调试信息 start
      if (log.isDebugEnabled()) {
    	  int count = ps.getParameterMetaData().getParameterCount();//1.获取prepareStatement对象对应的sql中参数的个数
    	  String sql_log=sql;
    	  try{
    		  for (int i = 0; i < count; i++) {//2.遍历所有参数，获取对应的参数值，并替换sql中的?
    			  //3.获取参数
    			  //3.1 判断获取的参数是否为空，如果为空，就赋值为null
    			  	//如果 null==parameters[i]时，执行parameters[i].toString()会抛出空指针异常 
    			  	//执行parameters[i].getClass()也会抛出空指针异常
    			  //3.2如果不为空，首先判断该参数是否是String类型，如果是，就给参数添加一对单引号（便于在数据库客户端工具中执行），否则，直接赋值
    			  String parameter = null != parameters[i]&&!"".equals(parameters[i]) ? 
    					  			parameters[i].getClass().getName().equals("java.lang.String") ? "'"+parameters[i].toString()+"'" : parameters[i].toString() : 
    					  			"null";
    					
				  sql_log = sql_log.replaceFirst("\\?", parameter);  
			  }    
    	  }catch(Exception e){
    		  log.debug("拼接sql异常:"+e.getMessage());
    		  e.printStackTrace();
    	  }
    	  log.info("===当前执行SQL为===" + sql_log );
      }
      //打印调试信息 end
      ps.execute();
      rows = ps.getUpdateCount();
    } finally {
      closeStatement(statementScope.getSession(), ps);
    }
    return rows;
  }

  /**
   * Adds a statement to a batch
   *
   * @param statementScope    - the request scope
   * @param conn       - the database connection
   * @param sql        - the sql statement
   * @param parameters - the parameters for the statement
   * @throws SQLException - if the statement fails
   */
  public void addBatch(StatementScope statementScope, Connection conn, String sql, Object[] parameters) throws SQLException {
    Batch batch = (Batch) statementScope.getSession().getBatch();
    if (batch == null) {
      batch = new Batch();
      statementScope.getSession().setBatch(batch);
    }
    batch.addBatch(statementScope, conn, sql, parameters);
  }

  /**
   * Execute a batch of statements
   *
   * @param sessionScope - the session scope
   * @return - the number of rows impacted by the batch
   * @throws SQLException - if a statement fails
   */
  public int executeBatch(SessionScope sessionScope) throws SQLException {
    int rows = 0;
    Batch batch = (Batch) sessionScope.getBatch();
    if (batch != null) {
      try {
        rows = batch.executeBatch();
      } finally {
        batch.cleanupBatch(sessionScope);
      }
    }
    return rows;
  }

  /**
   * Execute a batch of statements
   *
   * @param sessionScope - the session scope
   * @return - a List of BatchResult objects (may be null if no batch
   *         has been initiated).  There will be one BatchResult object in the
   *         list for each sub-batch executed
   * @throws SQLException   if a database access error occurs, or the drive
   *                        does not support batch statements
   * @throws BatchException if the driver throws BatchUpdateException
   */
public List executeBatchDetailed(SessionScope sessionScope) throws SQLException, BatchException {
    List answer = null;
    Batch batch = (Batch) sessionScope.getBatch();
    if (batch != null) {
      try {
        answer = batch.executeBatchDetailed();
      } finally {
        batch.cleanupBatch(sessionScope);
      }
    }
    return answer;
  }
  

  /**
   * 扩展点2
   * 覆盖了ibatis原生的sql执行方法
   * 在该方法中，主要是组装sqlCount语句
   * 根据不同的数据库类型，组装sql分页语句
   * @param statementScope
   * @param conn
   * @param sql
   * @param parameters
   * @param skipResults
   * @param maxResults
   * @param callback
   * @throws SQLException
   */
  public void executeQuery(StatementScope statementScope, Connection conn, String sql, Object[] parameters, int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
	  
	  ResultMap resultMap = statementScope.getResultMap();
	  
	  //取数据库产品名称   
      String dbName = resultMap.getId();//默认是：Oracle
      
      //如果是SqlCount查询
      if ((skipResults == -1 && maxResults == -1)) {//在使用的时候，传入两个参数（只是用于标识，没有特别的意义）-1，-1，表示进行sqlCount查询
//    	  String selectSql = removeFrom(sql);
//    	  int selectParametersLength = selectSql.split("\\?").length-1;//select语句中参数的个数
//    	  log.debug("selectSql is :"+selectSql);
//    	  log.debug("select ? count:"+selectParametersLength);
//    	  int newParamtersLength = parameters.length - selectParametersLength;
//    	  Object[] newParameters = new Object[newParamtersLength];
//    	  for(int i = 0;i< newParameters.length;i++){
//    		  newParameters[i] = parameters[selectParametersLength++];
//    	  }
//    	  parameters = newParameters;
//    	  for(int j=0;j<parameters.length;j++){
//    		  log.debug("&:"+parameters[j]);
//    	  }
    	  sql = "select count(1) count " + removeSelect(sql);//sqlCount最终的格式是：select count(1) count From xxxxxxxxxxxxxx
    	  
    	  sql = removeOrders(sql);//移走sql中的order by 部分，因为count查询不需要order by部分（这里注意，如果子查询中有order by，可能会有问题，所以这里也可以去掉）
    	  skipResults = NO_SKIPPED_RESULTS;//由于我们使用了这两个参数进行了标识，所以标识以后，应该参数重置，说明不使用ibatis提供的分页方式（因为ibatis提供的是逻辑分页）
    	  maxResults = NO_MAXIMUM_RESULTS;
      }
    	  
      int len = sql.length();   
         
      //如果是sql查询，并且是要求分页，我们就生成sql分页的查询语句   
      if ((skipResults != NO_SKIPPED_RESULTS || maxResults != NO_MAXIMUM_RESULTS)) {   
          //根据数据库产品名称取对应的分页SQL语句   
          sql = Dialect.getLimitString(dbName, sql, skipResults, maxResults);   
              
          if (sql.length() != len) {//如果条件成立，说明我们进行处理了原生的sql，所以这里应该进行参数重置，说明不使用ibatis提供的分页方式（因为ibatis提供的是逻辑分页）   
              skipResults = NO_SKIPPED_RESULTS;   
              maxResults = NO_MAXIMUM_RESULTS;   
          }   
      }   
      //以上主要是获取sqlCount的查询语句或sql分页的查询语句，然后调用ibatis框架原来的执行方法
      ibatisExecuteQuery(statementScope, conn, sql, parameters, skipResults, maxResults, callback);   

	  }
  
  /**
   * 扩展点2中使用的私有方法
   * 移除sql语句中的select部分，用select count(1) count替换
   * @param sql
   * @return
   */
  private String removeSelect(String sql) {
      Assert.hasText(sql);
      int beginPos = 0;
      if(-1 != sql.indexOf("FROM")){//说明sql中有大写的FROM（解决select中有子查询的情况）
    	  beginPos = sql.indexOf("FROM");
      }else{
    	  beginPos = sql.indexOf("from");
      }
      
      Assert.isTrue(beginPos != -1, " sql : " + sql + " must has a keyword 'FROM or from'");
      return sql.substring(beginPos);
  }
  /**
   * 扩展点2中使用的私有方法，暂时没用（保留）
   * @param sql
   * @return
   */
  @SuppressWarnings("unused")
private String removeFrom(String sql) {
      Assert.hasText(sql);
      int beginPos = 0;
      if(-1 != sql.indexOf("FROM")){//说明sql中有大写的FROM
    	  beginPos = sql.indexOf("FROM");
      }
      
      Assert.isTrue(beginPos != -1, " sql : " + sql + " must has a keyword 'FROM'");
      return sql.substring(0,beginPos);
  }
  /**
   * 扩展点2中使用的私有方法
   * @param sql
   * @return
   */
  private String removeOrders(String sql) {
      Assert.hasText(sql);
      Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
      Matcher m = p.matcher(sql);
      StringBuffer sb = new StringBuffer();
      while (m.find()) {
          m.appendReplacement(sb, "");
      }
      m.appendTail(sb);
      return sb.toString();
  }
  /**
   * Long form of the method to execute a query,ibatis原生的sql执行方法
   * 扩展点3，拼接sql，并log
   * @param statementScope     - the request scope
   * @param conn        - the database connection
   * @param sql         - the SQL statement to execute
   * @param parameters  - the parameters for the statement
   * @param skipResults - the number of results to skip
   * @param maxResults  - the maximum number of results to return
   * @param callback    - the row handler for the query
   * @throws SQLException - if the query fails
   */
  public void ibatisExecuteQuery(StatementScope statementScope, Connection conn, String sql, Object[] parameters, int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
    ErrorContext errorContext = statementScope.getErrorContext();
    errorContext.setActivity("executing query");
    errorContext.setObjectId(sql);
    PreparedStatement ps = null;
    ResultSet rs = null;
    setupResultObjectFactory(statementScope);
    try {
      errorContext.setMoreInfo("Check the SQL Statement (preparation failed).");
      Integer rsType = statementScope.getStatement().getResultSetType();
      if (rsType != null) {
        ps = prepareStatement(statementScope.getSession(), conn, sql, rsType);
      } else {
        ps = prepareStatement(statementScope.getSession(), conn, sql);
      }
      setStatementTimeout(statementScope.getStatement(), ps);
      Integer fetchSize = statementScope.getStatement().getFetchSize();
      if (fetchSize != null) {
        ps.setFetchSize(fetchSize.intValue());
      }
      errorContext.setMoreInfo("Check the parameters (set parameters failed).");
      statementScope.getParameterMap().setParameters(statementScope, ps, parameters);
      errorContext.setMoreInfo("Check the statement (query failed).");
      //打印调试信息 start
      if (log.isDebugEnabled()) {
    	  int count = ps.getParameterMetaData().getParameterCount();
    	  String sql_log=sql;
    	  try{
    		  for (int i = 0; i < count; i++) {
    			  //如果 null==parameters[i]时，执行parameters[i].toString()会抛出空指针异常 
    			  //执行parameters[i].getClass()也会抛出空指针异常
    			  String parameter = null != parameters[i]&&!"".equals(parameters[i]) ? 
    					  			parameters[i].getClass().getName().equals("java.lang.String") ? "'"+parameters[i].toString()+"'" : parameters[i].toString() : 
    					  			"null";
				  sql_log = sql_log.replaceFirst("\\?", parameter);  
			  }    
    	  }catch(Exception e){
    		  log.debug("拼接sql异常:"+e.getMessage());
    		  e.printStackTrace();
    	  }
    	  log.debug("===当前执行SQL为===" + sql_log );
      }
      //打印调试信息 end
      ps.execute();
      errorContext.setMoreInfo("Check the results (failed to retrieve results).");

      // Begin ResultSet Handling
      rs = handleMultipleResults(ps, statementScope, skipResults, maxResults, callback);
      // End ResultSet Handling
    } finally {
      try {
        closeResultSet(rs);
      } finally {
        closeStatement(statementScope.getSession(), ps);
      }
    }

  }

  /**
   * Execute a stored procedure that updates data
   *
   * @param statementScope    - the request scope
   * @param conn       - the database connection
   * @param sql        - the SQL to call the procedure
   * @param parameters - the parameters for the procedure
   * @return - the rows impacted by the procedure
   * @throws SQLException - if the procedure fails
   */
  public int executeUpdateProcedure(StatementScope statementScope, Connection conn, String sql, Object[] parameters) throws SQLException {
    ErrorContext errorContext = statementScope.getErrorContext();
    errorContext.setActivity("executing update procedure");
    errorContext.setObjectId(sql);
    CallableStatement cs = null;
    setupResultObjectFactory(statementScope);
    int rows = 0;
    try {
      errorContext.setMoreInfo("Check the SQL Statement (preparation failed).");
      cs = prepareCall(statementScope.getSession(), conn, sql);
      setStatementTimeout(statementScope.getStatement(), cs);
      ParameterMap parameterMap = statementScope.getParameterMap();
      ParameterMapping[] mappings = parameterMap.getParameterMappings();
      errorContext.setMoreInfo("Check the output parameters (register output parameters failed).");
      registerOutputParameters(cs, mappings);
      errorContext.setMoreInfo("Check the parameters (set parameters failed).");
      parameterMap.setParameters(statementScope, cs, parameters);
      errorContext.setMoreInfo("Check the statement (update procedure failed).");
      cs.execute();
      rows = cs.getUpdateCount();
      errorContext.setMoreInfo("Check the output parameters (retrieval of output parameters failed).");
      retrieveOutputParameters(statementScope, cs, mappings, parameters, null);
    } finally {
      closeStatement(statementScope.getSession(), cs);
    }
    return rows;
  }

  /**
   * Execute a stored procedure
   *
   * @param statementScope     - the request scope
   * @param conn        - the database connection
   * @param sql         - the sql to call the procedure
   * @param parameters  - the parameters for the procedure
   * @param skipResults - the number of results to skip
   * @param maxResults  - the maximum number of results to return
   * @param callback    - a row handler for processing the results
   * @throws SQLException - if the procedure fails
   */
  public void executeQueryProcedure(StatementScope statementScope, Connection conn, String sql, Object[] parameters, int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
    ErrorContext errorContext = statementScope.getErrorContext();
    errorContext.setActivity("executing query procedure");
    errorContext.setObjectId(sql);
    CallableStatement cs = null;
    ResultSet rs = null;
    setupResultObjectFactory(statementScope);
    try {
      errorContext.setMoreInfo("Check the SQL Statement (preparation failed).");
      Integer rsType = statementScope.getStatement().getResultSetType();
      if (rsType != null) {
        cs = prepareCall(statementScope.getSession(), conn, sql, rsType);
      } else {
        cs = prepareCall(statementScope.getSession(), conn, sql);
      }
      setStatementTimeout(statementScope.getStatement(), cs);
      Integer fetchSize = statementScope.getStatement().getFetchSize();
      if (fetchSize != null) {
        cs.setFetchSize(fetchSize.intValue());
      }
      ParameterMap parameterMap = statementScope.getParameterMap();
      ParameterMapping[] mappings = parameterMap.getParameterMappings();
      errorContext.setMoreInfo("Check the output parameters (register output parameters failed).");
      registerOutputParameters(cs, mappings);
      errorContext.setMoreInfo("Check the parameters (set parameters failed).");
      parameterMap.setParameters(statementScope, cs, parameters);
      errorContext.setMoreInfo("Check the statement (update procedure failed).");
      cs.execute();
      errorContext.setMoreInfo("Check the results (failed to retrieve results).");

      // Begin ResultSet Handling
      rs = handleMultipleResults(cs, statementScope, skipResults, maxResults, callback);
      // End ResultSet Handling
      errorContext.setMoreInfo("Check the output parameters (retrieval of output parameters failed).");
      retrieveOutputParameters(statementScope, cs, mappings, parameters, callback);

    } finally {
      try {
        closeResultSet(rs);
      } finally {
        closeStatement(statementScope.getSession(), cs);
      }
    }
  }

private ResultSet handleMultipleResults(PreparedStatement ps, StatementScope statementScope, int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
    ResultSet rs;
    rs = getFirstResultSet(statementScope, ps);
    if (rs != null) {
      handleResults(statementScope, rs, skipResults, maxResults, callback);
    }

    // Multiple ResultSet handling
    if (callback.getRowHandler() instanceof DefaultRowHandler) {
      MappedStatement statement = statementScope.getStatement();
      DefaultRowHandler defaultRowHandler = ((DefaultRowHandler) callback.getRowHandler());
      if (statement.hasMultipleResultMaps()) {
        List multipleResults = new ArrayList();
        multipleResults.add(defaultRowHandler.getList());
        ResultMap[] resultMaps = statement.getAdditionalResultMaps();
        int i = 0;
        while (moveToNextResultsSafely(statementScope, ps)) {
          if (i >= resultMaps.length) break;
          ResultMap rm = resultMaps[i];
          statementScope.setResultMap(rm);
          rs = ps.getResultSet();
          DefaultRowHandler rh = new DefaultRowHandler();
          handleResults(statementScope, rs, skipResults, maxResults, new RowHandlerCallback(rm, null, rh));
          multipleResults.add(rh.getList());
          i++;
        }
        defaultRowHandler.setList(multipleResults);
        statementScope.setResultMap(statement.getResultMap());
      } else {
        while (moveToNextResultsSafely(statementScope, ps)) ;
      }
    }
    // End additional ResultSet handling
    return rs;
  }

  private ResultSet getFirstResultSet(StatementScope scope, Statement stmt) throws SQLException {
    ResultSet rs = null;
    boolean hasMoreResults = true;
    while (hasMoreResults) {
      rs = stmt.getResultSet();
      
      if (rs != null) {
        break;
      }
      hasMoreResults = moveToNextResultsIfPresent(scope, stmt);
    }
    return rs;
  }

  private boolean moveToNextResultsIfPresent(StatementScope scope, Statement stmt) throws SQLException {
    boolean moreResults;
    // This is the messed up JDBC approach for determining if there are more results
    moreResults = !(((moveToNextResultsSafely(scope, stmt) == false) && (stmt.getUpdateCount() == -1)));
    return moreResults;
  }

  private boolean moveToNextResultsSafely(StatementScope scope, Statement stmt) throws SQLException {
    if (forceMultipleResultSetSupport(scope) || stmt.getConnection().getMetaData().supportsMultipleResultSets()) {
      return stmt.getMoreResults();
    }
    return false;
  }

  private boolean forceMultipleResultSetSupport(StatementScope scope) {
    return ((SqlMapClientImpl)scope.getSession().getSqlMapClient()).getDelegate().isForceMultipleResultSetSupport();
  }

  private void handleResults(StatementScope statementScope, ResultSet rs, int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
    try {
      statementScope.setResultSet(rs);
      ResultMap resultMap = statementScope.getResultMap();
      if (resultMap != null) {
        // Skip Results
        if (rs.getType() != ResultSet.TYPE_FORWARD_ONLY) {
          if (skipResults > 0) {
            rs.absolute(skipResults);
          }
        } 

        // Get Results
        int resultsFetched = 0;
        while ((maxResults == SqlExecutor.NO_MAXIMUM_RESULTS || resultsFetched < maxResults) && rs.next()) {
          Object[] columnValues = resultMap.resolveSubMap(statementScope, rs).getResults(statementScope, rs);
          callback.handleResultObject(statementScope, columnValues, rs);
          resultsFetched++;
        }
      }
    } finally {
      statementScope.setResultSet(null);
    }
  }

  private void retrieveOutputParameters(StatementScope statementScope, CallableStatement cs, ParameterMapping[] mappings, Object[] parameters, RowHandlerCallback callback) throws SQLException {
    for (int i = 0; i < mappings.length; i++) {
      ParameterMapping mapping = ((ParameterMapping) mappings[i]);
      if (mapping.isOutputAllowed()) {
        if ("java.sql.ResultSet".equalsIgnoreCase(mapping.getJavaTypeName())) {
          ResultSet rs = (ResultSet) cs.getObject(i + 1);
          ResultMap resultMap;
          if (mapping.getResultMapName() == null) {
            resultMap = statementScope.getResultMap();
            handleOutputParameterResults(statementScope, resultMap, rs, callback);
          } else {
            SqlMapClientImpl client = (SqlMapClientImpl) statementScope.getSession().getSqlMapClient();
            resultMap = client.getDelegate().getResultMap(mapping.getResultMapName());
            DefaultRowHandler rowHandler = new DefaultRowHandler();
            RowHandlerCallback handlerCallback = new RowHandlerCallback(resultMap, null, rowHandler);
            handleOutputParameterResults(statementScope, resultMap, rs, handlerCallback);
            parameters[i] = rowHandler.getList();
          }
          rs.close();
        } else {
          parameters[i] = mapping.getTypeHandler().getResult(cs, i + 1);
        }
      }
    }
  }

  private void registerOutputParameters(CallableStatement cs, ParameterMapping[] mappings) throws SQLException {
    for (int i = 0; i < mappings.length; i++) {
      ParameterMapping mapping = ((ParameterMapping) mappings[i]);
      if (mapping.isOutputAllowed()) {
        if (null != mapping.getTypeName() && !mapping.getTypeName().equals("")) { //@added
          cs.registerOutParameter(i + 1, mapping.getJdbcType(), mapping.getTypeName());
        } else {
          if (mapping.getNumericScale() != null && (mapping.getJdbcType() == Types.NUMERIC || mapping.getJdbcType() == Types.DECIMAL))
          {
            cs.registerOutParameter(i + 1, mapping.getJdbcType(), mapping.getNumericScale().intValue());
          } else {
            cs.registerOutParameter(i + 1, mapping.getJdbcType());
          }
        }
      }
    }
  }

  private void handleOutputParameterResults(StatementScope statementScope, ResultMap resultMap, ResultSet rs, RowHandlerCallback callback) throws SQLException {
    ResultMap orig = statementScope.getResultMap();
    try {
      statementScope.setResultSet(rs);
      if (resultMap != null) {
        statementScope.setResultMap(resultMap);

        // Get Results
        while (rs.next()) {
          Object[] columnValues = resultMap.resolveSubMap(statementScope, rs).getResults(statementScope, rs);
          callback.handleResultObject(statementScope, columnValues, rs);
        }
      }
    } finally {
      statementScope.setResultSet(null);
      statementScope.setResultMap(orig);
    }
  }

  /**
   * Clean up any batches on the session
   *
   * @param sessionScope - the session to clean up
   */
  public void cleanup(SessionScope sessionScope) {
    Batch batch = (Batch) sessionScope.getBatch();
    if (batch != null) {
      batch.cleanupBatch(sessionScope);
      sessionScope.setBatch(null);
    }
  }

  private PreparedStatement prepareStatement(SessionScope sessionScope, Connection conn, String sql, Integer rsType) throws SQLException {
    SqlMapExecutorDelegate delegate = ((SqlMapClientImpl) sessionScope.getSqlMapExecutor()).getDelegate();
    if (sessionScope.hasPreparedStatementFor(sql)) {
      return sessionScope.getPreparedStatement((sql));
    } else {
      PreparedStatement ps = conn.prepareStatement(sql, rsType.intValue(), ResultSet.CONCUR_READ_ONLY);
      sessionScope.putPreparedStatement(delegate, sql, ps);
      return ps;
    }
  }

  private CallableStatement prepareCall(SessionScope sessionScope, Connection conn, String sql, Integer rsType) throws SQLException {
    SqlMapExecutorDelegate delegate = ((SqlMapClientImpl) sessionScope.getSqlMapExecutor()).getDelegate();
    if (sessionScope.hasPreparedStatementFor(sql)) {
      return (CallableStatement) sessionScope.getPreparedStatement((sql));
    } else {
      CallableStatement cs = conn.prepareCall(sql, rsType.intValue(), ResultSet.CONCUR_READ_ONLY);
      sessionScope.putPreparedStatement(delegate, sql, cs);
      return cs;
    }
  }

  private static PreparedStatement prepareStatement(SessionScope sessionScope, Connection conn, String sql) throws SQLException {
    SqlMapExecutorDelegate delegate = ((SqlMapClientImpl) sessionScope.getSqlMapExecutor()).getDelegate();
    if (sessionScope.hasPreparedStatementFor(sql)) {
      return sessionScope.getPreparedStatement((sql));
    } else {
      PreparedStatement ps = conn.prepareStatement(sql);
      sessionScope.putPreparedStatement(delegate, sql, ps);
      return ps;
    }
  }

  private CallableStatement prepareCall(SessionScope sessionScope, Connection conn, String sql) throws SQLException {
    SqlMapExecutorDelegate delegate = ((SqlMapClientImpl) sessionScope.getSqlMapExecutor()).getDelegate();
    if (sessionScope.hasPreparedStatementFor(sql)) {
      return (CallableStatement) sessionScope.getPreparedStatement((sql));
    } else {
      CallableStatement cs = conn.prepareCall(sql);
      sessionScope.putPreparedStatement(delegate, sql, cs);
      return cs;
    }
  }

  private static void closeStatement(SessionScope sessionScope, PreparedStatement ps) {
    if (ps != null) {
      if (!sessionScope.hasPreparedStatement(ps)) {
        try {
          ps.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * @param rs
   */
  private static void closeResultSet(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        // ignore
      }
    }
  }

  private static void setStatementTimeout(MappedStatement mappedStatement, Statement statement) throws SQLException {
    if (mappedStatement.getTimeout() != null) {
    	System.out.println("mappedStatement.getTimeout().intValue()=="+mappedStatement.getTimeout().intValue());
      statement.setQueryTimeout(mappedStatement.getTimeout().intValue());
    }
  }

  //
  // Inner Classes
  //

  private static class Batch {
    private String currentSql;
	private List statementList = new ArrayList();
	private List batchResultList = new ArrayList();
    private int size;

    /**
     * Create a new batch
     */
    public Batch() {
      this.size = 0;
    }

    /**
     * Getter for the batch size
     *
     * @return - the batch size
     */
    @SuppressWarnings("unused")
	public int getSize() {
      return size;
    }

    /**
     * Add a prepared statement to the batch
     *
     * @param statementScope    - the request scope
     * @param conn       - the database connection
     * @param sql        - the SQL to add
     * @param parameters - the parameters for the SQL
     * @throws SQLException - if the prepare for the SQL fails
     */
	public void addBatch(StatementScope statementScope, Connection conn, String sql, Object[] parameters) throws SQLException {
      PreparedStatement ps = null;
      if (currentSql != null && currentSql.equals(sql)) {
        int last = statementList.size() - 1;
        ps = (PreparedStatement) statementList.get(last);
      } else {
        ps = prepareStatement(statementScope.getSession(), conn, sql);
        setStatementTimeout(statementScope.getStatement(), ps);
        currentSql = sql;
        statementList.add(ps);
        batchResultList.add(new BatchResult(statementScope.getStatement().getId(), sql));
      }
      statementScope.getParameterMap().setParameters(statementScope, ps, parameters);
      ps.addBatch();
      size++;
    }

    /**
     * 
     * and then removed in some even later release.  executeBatchDetailed gives
     * much more complete information.
     * <p/>
     * Execute the current session's batch
     *
     * @return - the number of rows updated
     * @throws SQLException - if the batch fails
     */
    public int executeBatch() throws SQLException {
      int totalRowCount = 0;
      for (int i = 0, n = statementList.size(); i < n; i++) {
        PreparedStatement ps = (PreparedStatement) statementList.get(i);
        int[] rowCounts = ps.executeBatch();
        for (int j = 0; j < rowCounts.length; j++) {
          if (rowCounts[j] == Statement.SUCCESS_NO_INFO) {
            // do nothing
          } else if (rowCounts[j] == Statement.EXECUTE_FAILED) {
            throw new SQLException("The batched statement at index " + j + " failed to execute.");
          } else {
            totalRowCount += rowCounts[j];
          }
        }
      }
      return totalRowCount;
    }

    /**
     * Batch execution method that returns all the information
     * the driver has to offer.
     *
     * @return a List of BatchResult objects
     * @throws BatchException (an SQLException sub class) if any nested
     *                        batch fails
     * @throws SQLException   if a database access error occurs, or the drive
     *                        does not support batch statements
     * @throws BatchException if the driver throws BatchUpdateException
     */
	public List executeBatchDetailed() throws SQLException, BatchException {
      List answer = new ArrayList();
      for (int i = 0, n = statementList.size(); i < n; i++) {
        BatchResult br = (BatchResult) batchResultList.get(i);
        PreparedStatement ps = (PreparedStatement) statementList.get(i);
        try {
          br.setUpdateCounts(ps.executeBatch());
        } catch (BatchUpdateException e) {
          StringBuffer message = new StringBuffer();
          message.append("Sub batch number ");
          message.append(i + 1);
          message.append(" failed.");
          if (i > 0) {
            message.append(" ");
            message.append(i);
            message.append(" prior sub batch(s) completed successfully, but will be rolled back.");
          }
          throw new BatchException(message.toString(), e, answer, br.getStatementId(), br.getSql());
        }
        answer.add(br);
      }
      return answer;
    }

    /**
     * Close all the statements in the batch and clear all the statements
     *
     * @param sessionScope
     */
    public void cleanupBatch(SessionScope sessionScope) {
      for (int i = 0, n = statementList.size(); i < n; i++) {
        PreparedStatement ps = (PreparedStatement) statementList.get(i);
        closeStatement(sessionScope, ps);
      }
      currentSql = null;
      statementList.clear();
      batchResultList.clear();
      size = 0;
    }
  }

  private void setupResultObjectFactory(StatementScope statementScope) {
    SqlMapClientImpl client = (SqlMapClientImpl) statementScope.getSession().getSqlMapClient();
    ResultObjectFactoryUtil.setResultObjectFactory(client.getResultObjectFactory());
    ResultObjectFactoryUtil.setStatementId(statementScope.getStatement().getId());
  }
}
