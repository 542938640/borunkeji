package com.taro.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DbtableUtil {
	 public static Connection getConnection() throws Exception{
	        String driver = PropertyUtil.getProperty("jdbc.properties", "jdbc.driver");
	        String url = PropertyUtil.getProperty("jdbc.properties", "jdbc.url"); //DMEO为数据库名
	        String user = PropertyUtil.getProperty("jdbc.properties", "jdbc.username");
	        String password = PropertyUtil.getProperty("jdbc.properties", "jdbc.password");
	        try{
	            Class.forName(driver);
	            Connection conn = DriverManager.getConnection(url, user, password);
	            return conn;
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	public JSONArray tableResult(String tableName,String time) throws Exception{
		JSONArray array = new JSONArray();
		 Connection oracle_conn = null;  
	        Statement oracle_stmt = null;  
	        ResultSet oracle_rs = null; 
	        ResultSetMetaData rsm = null;
	        String sql = "";
	        try {
	        		if(!time.equals("")){
	        			sql = "select * from "+tableName+" t WHERE t.F_CREATE_TIME > '"+time+"' or t.F_LAST_MODIFIED_TIME > '"+time+"'";
	        		}else{
	        			sql = "select * from "+tableName+" t  ";
	        		}
	        		  
	        	
	    		oracle_conn = getConnection();  
	            oracle_stmt = oracle_conn.createStatement(); 
	            oracle_rs = oracle_stmt.executeQuery(sql);
	            rsm = oracle_rs.getMetaData();
	            int num = rsm.getColumnCount();
	            while (oracle_rs.next()) {
		            JSONObject mapOfColValues = new JSONObject();
		            for (int i = 1; i <= num; i++) {
		            	if(oracle_rs.getObject(i) == null){
		            		 mapOfColValues.put(rsm.getColumnName(i), "");
		            	}else{
		            		 mapOfColValues.put(rsm.getColumnName(i), oracle_rs.getObject(i));
		            	}
		            }
		            array.add(mapOfColValues);
		            }
			} catch (Exception e) {
				// TODO: handle exception
			}finally {  
	            try {  
	                if(oracle_rs != null) {  
	                    oracle_rs.close();  
	                    oracle_rs = null;  
	                }  
	                  
	                if(oracle_stmt != null) {  
	                    oracle_stmt.close();  
	                    oracle_stmt = null;  
	                }  
	                  
	                if(oracle_conn != null) {  
	                    oracle_conn.close();  
	                    oracle_conn = null;  
	                }  
	            }catch (Exception e) {
					// TODO: handle exception
				}
	            }
	        return  array;
		
	}
	public int tableInsertResult(String tableName,JSONArray insertResult) throws Exception{
		int cout = 0;
		long startTime = 0;
		 JSONObject o = insertResult.getJSONObject(0);
	       Set<String> keys =  o.keySet();
	       String column = "";
	       String value = "";
	       for(String key:keys){
				column += key+",";
				value+="?,";
			}
	       column = column.substring(0 , column.lastIndexOf(","));
	       value = value.substring(0 , value.lastIndexOf(","));
	       String [] columns = column.split(",");
	        Connection connection = null;
			try{
	        	   connection = getConnection();
	            startTime=System.currentTimeMillis();
	            connection.setAutoCommit(false);
	            PreparedStatement statement = connection.prepareStatement("INSERT INTO "+tableName+" ("+column+") VALUES ( "+value+" ) ");
	            int num = 0;
	            
	            for (int i = 0; i < insertResult.size(); i++) {
	 	    	   JSONObject insertO= insertResult.getJSONObject(i);
	 	    	   for(int x = 0; x < columns.length; x++){
	 	    		  statement.setObject(x+1, insertO.get(columns[x]));
	 	    	   }
	 	    	  statement.addBatch();
	 	    	  num++;
	                if(num !=0 && num%500 == 0){
	                    statement.executeBatch();
	                    connection.commit();
	                    num = 0;
	                }
	                cout++;
	 		   }
	            statement.executeBatch();
	            connection.commit();
	        }catch (Exception e){
	        	cout = 0 ;
	            e.printStackTrace();
	            connection.rollback();
	        }finally {
	            if(connection != null){
	                connection.close();
	            }
	            long endTime=System.currentTimeMillis();
	            System.out.println("方法执行时间："+(endTime-startTime)+"ms");
	        }
		return cout;
	}
}
