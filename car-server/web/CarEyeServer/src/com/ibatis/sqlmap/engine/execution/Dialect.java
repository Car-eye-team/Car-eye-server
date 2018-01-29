package com.ibatis.sqlmap.engine.execution;
/**
 * 数据库方言类
 * 将普通sql语句，转换为指定的数据库特有的分页sql
 * 目前支持的有Mysql和Oracle数据库
 * @author sl
 *
 */
public class Dialect {
	private static final String SQL_END_DELIMITER = ";"; //sql语句的结束符   
	/**
	 * 转换为指定数据库类型的分页sql语句  
	 */
    public static String getLimitString(String dbName, String sql, int offset,   
            int limit) {   
        String limitString = sql;   
        if (dbName.indexOf("mysql") != -1) {   
            limitString = getMysqlLimitString(sql, offset, limit);   
        }   
        if (dbName.indexOf("sqlserver") != -1) {   
            limitString = getMssqlLimitString(sql, offset, limit);   
        }   
        if (dbName.indexOf("oracle") != -1) {   
            limitString = getOracleLimitString(sql, offset, limit);   
        }   
        if (dbName.indexOf("db2") != -1) {   
            limitString = getDB2LimitString(sql, offset, limit);   
        }else{
        	 limitString = getOracleLimitString(sql, offset, limit); 
        } 
           
        return limitString;
    }   
    
    /**
     * 私有方法，内部使用
     * 将sql语句转换为Mysql数据库特有的分页sql
     * @param sql
     * @param offset
     * @param limit
     * @return
     *
     */
    private static String getMysqlLimitString(String sql, int offset, int limit) {   
        sql = trim(sql);   
        StringBuffer sb = new StringBuffer();   
        sb.append(sql);   
        if (offset > 0) {   
            sb.append(" limit ").append(offset).append(',').append(limit);   
        } else {   
            sb.append(" limit ").append(limit);   
        }   
        return sb.toString();   
    }   
    
    /**
     * 私有方法，内部使用
     * 将sql语句转换为Oracle数据库特有的分页sql
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    private static String getOracleLimitString(String sql, int offset, int limit) {   
        sql = trim(sql);   
        StringBuffer sb = new StringBuffer();   
        
        sb.append("select * from (select row_.*, rownum rownum_ from ( ")   
                .append(sql)
                .append(" ) row_ where rownum <= ")
                .append(offset + limit)
                .append(") where rownum_ >")
                .append(offset);
        return sb.toString();   
    }   
 
    
    /**
     * 私有方法，内部使用
     * 将sql语句转换为Mssql数据库特有的分页sql，目前没有实现
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    private static String getMssqlLimitString(String sql, int offset, int limit) {   
        return null;   
    }   
    
    /**
     * 私有方法，内部使用
     * 将sql语句转换为DB2数据库特有的分页sql，目前没有实现
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    private static String getDB2LimitString(String sql, int offset, int limit) {           
        return null;   
    }   
    
    /**
     * 私有方法，内部使用
     * 去掉sql语句的结束符
     * @param sql
     * @return
     */
    private static String trim(String sql) {   
        sql = sql.trim();   
        if (sql.endsWith(SQL_END_DELIMITER)) {   
            sql = sql.substring(0, sql.length() - 1 - SQL_END_DELIMITER.length());   
        }   
        return sql;   
    }   

}
