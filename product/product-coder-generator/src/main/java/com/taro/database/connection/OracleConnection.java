package com.taro.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.taro.database.DbConnection;

public class OracleConnection implements DbConnection {
	public Connection getConnection(String type, String host, String port, String database, String username, String password) throws Exception {
	    Connection conn = null;
	    Properties props = new Properties();
	    
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    if(StringUtils.isBlank(port)) {
	    	port = "1521";
	    }
	    String url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + database;
	    
	    props.setProperty("user", username);
	    props.setProperty("password", password);
	    props.setProperty("remarks", "true");
	    props.setProperty("remarksReporting", "true");
	    
	    conn = DriverManager.getConnection(url, props);
	    
	    return conn;
	}
}
