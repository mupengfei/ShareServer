package com.jesse.share.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GlobalConfig {
	private static final String CONFIG_FILE_PATH = "WEB-INF" + File.separator + "config" + File.separator +  "GlobalConfig.properties";
	private final Properties properties = new Properties();

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
			in = new FileInputStream(realPath);
			properties.load(in);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String get(String key) {
		return properties.getProperty(key);
	}
}
