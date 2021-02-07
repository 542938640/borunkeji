package com.taro.database;

import java.sql.Connection;

public abstract interface DbConnection {
	public abstract Connection getConnection(String type, String host, String port, String database, String username, String password) throws Exception;
}
