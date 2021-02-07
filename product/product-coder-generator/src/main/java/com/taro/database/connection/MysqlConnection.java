package com.taro.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taro.database.DbConnection;

public class MysqlConnection implements DbConnection {
    private final static Logger LOGGER = LoggerFactory.getLogger(MysqlConnection.class);

	public Connection getConnection(String dbType, String host, String port, String database, String username, String password) {
	    Connection conn = null;
    	if(StringUtils.isBlank(port)) {
    		port = "3306";
    	}
    	if(StringUtils.isBlank(dbType)) {
    		dbType = "mysql";
    	}
    	
    	String driver = "com.mysql.jdbc.Driver";
    	String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=UTF8";
    	
    	if("mysql8".equals(dbType)) {
    		driver = "com.mysql.cj.jdbc.Driver";
    		url += "&useSSL=false&serverTimezone=UTC&autoReconnect=true&failOverReadOnly=false";
    	}
    	
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);
        }
	    return conn;
	}
}
