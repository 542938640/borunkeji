package com.taro.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taro.codegenerator.entity.TableColumnEntity;
import com.taro.codegenerator.entity.TableInfoEntity;

public class DatabaseUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil.class);

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/product?useSSL=false&characterEncoding=utf-8&serverTimezone=UTC&autoReconnect=true&failOverReadOnly=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    /**
     * -获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * -关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
            }
        }
    }

    public static TableInfoEntity getTableInfo(Connection conn, String table_name) {
        List<TableInfoEntity> tableInfoList = new ArrayList<TableInfoEntity>();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(conn.getCatalog(), db.getUserName(), table_name, new String[] { "TABLE" });
            TableInfoEntity tableInfo = null;
            while(rs.next()) {
            	tableInfo = new TableInfoEntity();
            	tableInfo.setTable_cat(rs.getString("TABLE_CAT"));
            	tableInfo.setTable_schem(rs.getString("TABLE_SCHEM"));
            	tableInfo.setTable_name(rs.getString("TABLE_NAME"));
            	tableInfo.setTable_type(rs.getString("TABLE_TYPE"));
            	tableInfo.setRemarks(rs.getString("REMARKS"));
                tableInfoList.add(tableInfo);
            }
            if(CollectionUtils.isNotEmpty(tableInfoList)) {
            	return tableInfoList.get(0);
            }
        } catch (SQLException e) {
            LOGGER.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.error("close ResultSet failure", e);
            }
        }
    	return null;
    }
    
    public static List<TableColumnEntity> getTableColumns(Connection conn, String table_name) {
    	List<TableColumnEntity> tableColumnList = new ArrayList<TableColumnEntity>();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的列
            rs = db.getColumns(conn.getCatalog(), db.getUserName(), table_name, "%");
            TableColumnEntity tableColumn = null;
            while(rs.next()) {
            	String column_name = rs.getString("COLUMN_NAME");
            	String type_name = rs.getString("TYPE_NAME");
            	if(StringUtils.isBlank(column_name) || StringUtils.isBlank(type_name)) {
            		continue;
            	}
            	
            	String field_name = column_name.toLowerCase();// 实体对应名称
            	String field_type = type_name.toLowerCase();// 实体对应类型
            	
            	if(type_name.toUpperCase().equals("INT")) {
            		type_name = "INTEGER";
            	}
            	
            	System.out.println(field_name + ": " +field_type);
            	
            	// 基类属性没必要生成
            	if("f_pid".equals(field_name)
            			|| "f_remark".equals(field_name)
            			|| "f_sys_flag".equals(field_name)
            			|| "f_last_modifier".equals(field_name)
            			|| "f_last_modified_time".equals(field_name)
            			|| "f_creator".equals(field_name)
            			|| "f_create_time".equals(field_name)) {
            		continue;
            	}
//            	if("f_pid".equals(field_name)) {
//            		field_name = "id";
//            	}
            	if(field_name.indexOf("f_") != -1) {
            		field_name = field_name.substring(2);
            	}
            	if ((field_type.contains("char")) || (field_type.contains("clob")) || (field_type.contains("text"))) {
            		field_type = "String";
                } else if (field_type.equals("blob")) {
                	field_type = "byte[]";
                } else if ((field_type.contains("date")) || (field_type.contains("time"))) {
                	field_type = "Date";
                } else if (field_type.contains("float") || (field_type.contains("double"))) {
                	field_type = "Double";
                } else if ((field_type.indexOf("number,decimal,integer,float,real;float;double") > 0) || (field_type.contains("int"))) {
                	field_type = "Integer";
                } else {
                	field_type = "String";
                }
            	
            	tableColumn = new TableColumnEntity();
            	tableColumn.setColumn_name(column_name);
            	tableColumn.setType_name(type_name);
            	tableColumn.setColumn_size(rs.getInt("COLUMN_SIZE"));
            	tableColumn.setDecimal_digits(rs.getInt("DECIMAL_DIGITS"));
            	tableColumn.setNum_prec_radix(rs.getInt("NUM_PREC_RADIX"));
            	tableColumn.setRemarks(rs.getString("REMARKS"));
            	tableColumn.setColumn_def(rs.getString("COLUMN_DEF"));
            	tableColumn.setIs_nullable(rs.getString("IS_NULLABLE"));
            	tableColumn.setField_name(field_name);
            	tableColumn.setField_type(field_type);
            	tableColumnList.add(tableColumn);
            }
            if(CollectionUtils.isNotEmpty(tableColumnList)) {
            	return tableColumnList;
            }
        } catch (SQLException e) {
            LOGGER.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.error("close ResultSet failure", e);
            }
        }
    	return null;
    }
    
    public static String getTablePrimaryKey(Connection conn, String table_name) {
    	String primaryKey = "";
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的主键
            rs = db.getPrimaryKeys(conn.getCatalog(), db.getUserName(), table_name);  
            while(rs.next()) {
            	primaryKey = rs.getString("COLUMN_NAME");  
            }
        } catch (SQLException e) {
            LOGGER.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.error("close ResultSet failure", e);
            }
        }
    	return primaryKey;
    }
    
}
