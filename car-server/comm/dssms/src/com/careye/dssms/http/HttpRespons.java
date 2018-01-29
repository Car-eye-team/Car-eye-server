/* car-eye车辆管理平台 
 * car-eye车辆管理公共平台   www.car-eye.cn
 * car-eye开源网址:  https://github.com/Car-eye-admin
 * Copyright car-eye 车辆管理平台  2017 
 */

package com.careye.dssms.http;

import java.util.Vector;

/**    
 *     
 * 项目名称：dssms    
 * 类名称：HttpRespons    
 * 类描述：    
 * 创建人：zr    
 * 创建时间：2015-6-24 下午07:49:04    
 * 修改人：zr    
 * 修改时间：2015-6-24 下午07:49:04    
 * 修改备注：    
 * @version 1.0  
 *     
 */
public class HttpRespons {   
    
    String urlString;   
    
    int defaultPort;   
    
    String file;   
     
    String host;   
    
    String path;   
    
    int port;   
    
    String protocol;   
    
    String query;   
    
    String ref;   
    
    String userInfo;   
    
    String contentEncoding;   
    
    String content;   
    
    String contentType;   
    
    int code;   
    
    String message;   
    
    String method;   
    
    int connectTimeout;   
    
    int readTimeout;   
    
    Vector<String> contentCollection;   
    
    public String getContent() {   
        return content;   
    }   
    
    public String getContentType() {   
        return contentType;   
    }   
    
    public int getCode() {   
        return code;   
    }   
    
    public String getMessage() {   
        return message;   
    }   
    
    public Vector<String> getContentCollection() {   
        return contentCollection;   
    }   
    
    public String getContentEncoding() {   
        return contentEncoding;   
    }   
    
    public String getMethod() {   
        return method;   
    }   
    
    public int getConnectTimeout() {   
        return connectTimeout;   
    }   
    
    public int getReadTimeout() {   
        return readTimeout;   
    }   
    
    public String getUrlString() {   
        return urlString;   
    }   
    
    public int getDefaultPort() {   
        return defaultPort;   
    }   
    
    public String getFile() {   
        return file;   
    }   
    
    public String getHost() {   
        return host;   
    }   
    
    public String getPath() {   
        return path;   
    }   
    
    public int getPort() {   
        return port;   
    }   
    
    public String getProtocol() {   
        return protocol;   
    }   
    
    public String getQuery() {   
        return query;   
    }   
    
    public String getRef() {   
        return ref;   
    }   
    
    public String getUserInfo() {   
        return userInfo;   
    }   
    
}
