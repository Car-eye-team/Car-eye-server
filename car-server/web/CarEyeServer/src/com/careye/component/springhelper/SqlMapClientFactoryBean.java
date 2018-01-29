package com.careye.component.springhelper;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.springframework.core.io.Resource;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * 加载ibatis配置类.
 * 扩展了spring的加载类，支持多ibatis配置文件加载.
 * @author xiongzy
 *
 */
@SuppressWarnings("unchecked")
public class SqlMapClientFactoryBean extends
        org.springframework.orm.ibatis.SqlMapClientFactoryBean {
    
    /**
     * 加载ibatis配置文件.
     * 支持加载多个sqlMapConfig文件（方便按模板配置sqlMapConfig.xml）
     * @param configLocation 配置文件资源
     * @param properties 属性集 
     * @return SqlMapClient 
     * @throws IOException  io异常
     */
   
	protected SqlMapClient buildSqlMapClient(Resource configLocation, Properties properties) throws IOException {
        /// 得到ibatis的sqlMapConfig路径
        String path = configLocation.getFilename();
        if (path == null || path.length() == 0) {
            throw new IOException("ibatis sqlMapConfig file not config");
        }
        
        /// 多个sqlMapConfig文件通过","号分隔(每个子系统一个)
        String[] sqlMapConfigFiles = path.split(",");
        int size = sqlMapConfigFiles.length;
        
        // 将多个sqlMapConfig文件拼成一个DOM
        Document totalDocument = null;
        try {
            totalDocument = getDocument(Resources.getResourceAsReader(sqlMapConfigFiles[0]));
        } catch (JDOMException e) {
            throw new IOException("parse ibatis sqlMapConfig file failed, file:" 
                    + sqlMapConfigFiles[0] + "JDOMException:" + e.getMessage());
        } catch (IOException e) {
            throw new IOException("read ibatis sqlMapConfig file failed, file:" 
                    + sqlMapConfigFiles[0] + "JDOMException:" + e.getMessage());
        }
        
        for (int i = 1; i < size; i++) {
            String sqlMapConfigFile = sqlMapConfigFiles[i];
            Reader subReader = Resources.getResourceAsReader(sqlMapConfigFile.trim());
            try {
                Document subDocument = getDocument(subReader);
                addDocument(totalDocument, subDocument);
            } catch (JDOMException e) {
                throw new IOException("parse ibatis sqlMapConfig file failed, file:" + sqlMapConfigFile 
                                       + "JDOMException:" + e.getMessage());
            } catch (IOException e) {
                throw new IOException("read ibatis sqlMapConfig file failed, file:" + sqlMapConfigFile
                                       + "IOException:" + e.getMessage());
            }
        }
        
        // 将全量的DOM转成输入流
        XMLOutputter xmlOutputter = new XMLOutputter();
        StringWriter tmpStringWriter = new StringWriter();
        xmlOutputter.output(totalDocument, tmpStringWriter);
        StringReader totalStringReader = new StringReader(tmpStringWriter.toString());
        return SqlMapClientBuilder.buildSqlMapClient(totalStringReader, properties);
    }
    
    /**
     * 拼装DOM.
     * @param base 全量DOM
     * @param sub 子系统DOM
     */
    private void addDocument(Document base ,Document sub){
        List list = sub.getRootElement().getChildren();
        int size = list.size();
        for (int i = 0; i < size; i++) {
          Element sqlMap = (Element) list.get(0);
          base.getRootElement().addContent(sqlMap.detach());
        }
    }

    /**
     * 将xml文件转成DOM.
     * @param reader xml文件
     * @return DOM
     * @throws IOException IO异常
     * @throws JDOMException JDOM异常
     */
    private Document  getDocument(Reader reader) throws IOException, JDOMException{
        SAXBuilder builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser",false);
        builder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        Document sqlmapDoc = builder.build(reader);
        return sqlmapDoc;
    }

}
