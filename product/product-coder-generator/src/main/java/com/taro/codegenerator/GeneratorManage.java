package com.taro.codegenerator;import java.io.StringWriter;import java.io.Writer;import java.sql.Connection;import java.sql.SQLException;import java.text.SimpleDateFormat;import java.util.Date;import java.util.HashMap;import java.util.Map;import org.apache.commons.lang3.StringUtils;import com.taro.codegenerator.entity.ConfigEntity;import com.taro.database.DbManage;import com.taro.exception.BusinessException;import com.taro.utils.DatabaseUtil;import com.taro.utils.FileUtil;import freemarker.template.Configuration;import freemarker.template.Template;public class GeneratorManage {		private static Configuration configuration;		static {		configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);		configuration.setClassForTemplateLoading(GeneratorManage.class, "/");		configuration.setDefaultEncoding("UTF-8");	}	public static Map<String, Object> getTableParam(ConfigEntity config) {				Connection conn = null;		try {			conn = new DbManage(config.getDb_type())				.getConnection(config.getDb_host(), config.getDb_port(), config.getDb_database(), config.getDb_username(), config.getDb_password());		} catch (Exception e) {			e.printStackTrace();		}		if(null == conn) {			throw new BusinessException("数据库连接失败!");		}				// 变量map		Map<String, Object> param = new HashMap<String, Object>();		param.put("config", config);		param.put("tableInfo", DatabaseUtil.getTableInfo(conn, config.getTable_name()));		param.put("tableColumn", DatabaseUtil.getTableColumns(conn, config.getTable_name()));		param.put("tablePrimaryKey", DatabaseUtil.getTablePrimaryKey(conn, config.getTable_name()));		        try {        	conn.close();        } catch (SQLException e) {			throw new BusinessException("数据库关闭失败!");        }				return param;	}		public static void generate(ConfigEntity config) {		if(null == config) {			throw new BusinessException("配置为空,生成失败!");		}else if(StringUtils.isBlank(config.getDb_type())) {			throw new BusinessException("数据库类型为空,生成失败!");		}else if(StringUtils.isBlank(config.getDb_host())) {			throw new BusinessException("数据库主机名或ip地址为空,生成失败!");		}else if(StringUtils.isBlank(config.getDb_port())) {			throw new BusinessException("数据库端口为空,生成失败!");		}else if(StringUtils.isBlank(config.getDb_database())) {			throw new BusinessException("数据库名为空,生成失败!");		}else if(StringUtils.isBlank(config.getDb_username())) {			throw new BusinessException("数据库用户名为空,生成失败!");		}else if(StringUtils.isBlank(config.getDb_password())) {			throw new BusinessException("数据库密码为空,生成失败!");		}else if(StringUtils.isBlank(config.getTable_name())) {			throw new BusinessException("表名为空,生成失败!");		}else if(StringUtils.isBlank(config.getClass_name())) {			throw new BusinessException("类名为空,生成失败!");		}else if(StringUtils.isBlank(config.getPackage_name())) {			throw new BusinessException("包名为空,生成失败!");		}else if(StringUtils.isBlank(config.getModule_name())) {			throw new BusinessException("模块名为空,生成失败!");		}				// 变量名(首字母小写)		config.setGenerator_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));				Map<String, Object> param = getTableParam(config);		if(null == param) {			throw new BusinessException("变量错误,生成失败!");		}				// java路径		if(StringUtils.isNotBlank(config.getJava_url())) {			String java_url = config.getJava_url();			String resources_url = config.getJava_url();			if(java_url.indexOf("\\src\\main") == -1) {				java_url += "\\src\\main\\java";				resources_url += "\\src\\main\\resources";			}else {				resources_url.replace("java", "resources");			}			String[] packages = config.getPackage_name().split("\\.");			if(null != packages) {				for(String package_name : packages) {					java_url += "\\" + package_name;				}			}			generateController(java_url, config, param);			generateDao(java_url, config, param);			generateEntity(java_url, config, param);			generateService(java_url, config, param);			generateServiceImpl(java_url, config, param);			generateMapper(resources_url, config, param);		}	}		public static void generateController(String java_url, ConfigEntity config, Map<String, Object> param) {		if(StringUtils.isNotBlank(java_url) && null != param) {			Writer out = new StringWriter();			try {				Template template = configuration.getTemplate("java/controller.ftl");				template.process(param, out);				FileUtil.writerFile(java_url + "\\controller\\" + config.getModule_name() + "\\" + config.getClass_name() + "Controller.java", out.toString());			} catch (Exception e) {				System.out.println("Controller生成失败!");				e.printStackTrace();			}		}	}	public static void generateDao(String java_url, ConfigEntity config, Map<String, Object> param) {		if(StringUtils.isNotBlank(java_url) && null != param) {			Writer out = new StringWriter();			try {				Template template = configuration.getTemplate("java/dao.ftl");				template.process(param, out);				FileUtil.writerFile(java_url + "\\dao\\" + config.getModule_name() + "\\" + config.getClass_name() + "Dao.java", out.toString());			} catch (Exception e) {				System.out.println("Dao生成失败!");				e.printStackTrace();			}		}	}	public static void generateEntity(String java_url, ConfigEntity config, Map<String, Object> param) {		if(StringUtils.isNotBlank(java_url) && null != param) {			Writer out = new StringWriter();			try {				Template template = configuration.getTemplate("java/entity.ftl");				template.process(param, out);				FileUtil.writerFile(java_url + "\\entity\\" + config.getModule_name() + "\\" + config.getClass_name() + "Entity.java", out.toString());			} catch (Exception e) {				System.out.println("Entity生成失败!");				e.printStackTrace();			}		}	}		public static void generateService(String java_url, ConfigEntity config, Map<String, Object> param) {		if(StringUtils.isNotBlank(java_url) && null != param) {			Writer out = new StringWriter();			try {				Template template = configuration.getTemplate("java/service.ftl");				template.process(param, out);				FileUtil.writerFile(java_url + "\\service\\" + config.getModule_name() + "\\" + config.getClass_name() + "Service.java", out.toString());			} catch (Exception e) {				System.out.println("Service生成失败!");				e.printStackTrace();			}		}	}	public static void generateServiceImpl(String java_url, ConfigEntity config, Map<String, Object> param) {		if(StringUtils.isNotBlank(java_url) && null != param) {			Writer out = new StringWriter();			try {				Template template = configuration.getTemplate("java/serviceImpl.ftl");				template.process(param, out);				FileUtil.writerFile(java_url + "\\service\\" + config.getModule_name() + "\\impl\\" + config.getClass_name() + "ServiceImpl.java", out.toString());			} catch (Exception e) {				System.out.println("ServiceImpl生成失败!");				e.printStackTrace();			}		}	}		public static void generateMapper(String java_url, ConfigEntity config, Map<String, Object> param) {		if(StringUtils.isNotBlank(java_url) && null != param) {			Writer out = new StringWriter();			try {				Template template = configuration.getTemplate("java/mapper.ftl");				template.process(param, out);				FileUtil.writerFile(java_url + "\\mybatis\\" + config.getModule_name() + "\\" + config.getClass_name() + "Mapper.xml", out.toString());			} catch (Exception e) {				System.out.println("Mapper生成失败!");				e.printStackTrace();			}		}	}		public static void main(String[] args) {		ConfigEntity config = new ConfigEntity();		config.setDb_type("mysql8");		config.setDb_host("127.0.0.1");		config.setDb_port("3306");		config.setDb_database("pro");		config.setDb_username("root");		config.setDb_password("root");		config.setTable_name("t_finance_note_detail");		config.setClass_name("FinanceNoteDetail");		config.setPackage_name("com.taro");		config.setModule_name("finance");		config.setJava_url("D:\\Eclipse-WorkSpace\\taro\\product\\product-business");		config.setAuthor("张宇唯");		generate(config);	}}