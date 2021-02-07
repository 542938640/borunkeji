package com.taro.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.taro.database.connection.MysqlConnection;
import com.taro.database.connection.OracleConnection;

public class DbManage {
	private String dbType;
	private DbConnection dbConnection;
  
	public DbManage() {}
  
	public DbManage(String dbType) {
		this.dbType = dbType;
	    if (dbType.equalsIgnoreCase("oracle")) {
	    	this.dbConnection = new OracleConnection();
	    } else if (dbType.indexOf("mysql") != -1) {
	    	this.dbConnection = new MysqlConnection();
	    } else {
	    	this.dbConnection = null;
	    }
	}
  
	public Connection getConnection(String host, String port, String database, String username, String password) throws Exception {
	    if (this.dbConnection != null) {
	    	return this.dbConnection.getConnection(dbType, host, port, database, username, password);
	    }
	    return null;
	}
  
	public DatabaseMetaData getMetaData(Connection connection) {
		DatabaseMetaData metaData = null;
		if (connection != null) {
			try {
				metaData = connection.getMetaData();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return metaData;
	}
}
