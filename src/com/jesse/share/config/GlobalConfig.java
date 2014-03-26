package com.jesse.share.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; 

public class GlobalConfig {
	private Logger logger = LogManager.getLogger(GlobalConfig.class.getName());  
	private static final String CONFIG_FILE_PATH = "WEB-INF" + File.separator + "config" + File.separator;
	private final Properties properties = new Properties();
	private final Properties sqls = new Properties();

	public static GlobalConfig getInstance() {
		return GlobalConfig.GlobalConfigHolder.instance;
	}

	private static class GlobalConfigHolder {
		private static GlobalConfig instance = new GlobalConfig();
	}

	private GlobalConfig() {
		InputStream in = null;
		try {
			String rootUrl = GlobalConfig.class.getResource("/").getPath().replace("%20", " ");
			String realPath = rootUrl.substring(0, rootUrl.indexOf("WEB-INF")) + CONFIG_FILE_PATH;
			in = new FileInputStream(realPath + "GlobalConfig.properties");
			properties.load(in);
			in = new FileInputStream(realPath + "GlobalSql.sql");
			sqls.load(in);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.catching(e1);
		} catch (IOException e) {
			logger.catching(e);
		}
	}

	public String get(String key) {
		return properties.getProperty(key);
	}

	public String getSql(String key) {
		return sqls.getProperty(key);
	}
}
