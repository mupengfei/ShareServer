package com.jesse.share.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jesse.share.config.GlobalConfig;

public class DBHelper {
	private static Logger logger = LogManager.getLogger(DBHelper.class.getName());  
	private static final GlobalConfig GLOBAL_CONFIG = GlobalConfig.getInstance();
	private static final String driverString = "com.mysql.jdbc.Driver";

	private static final String DB_USERNAME = GLOBAL_CONFIG.get("DB_USERNAME");

	private static final String DB_PASSWORD = GLOBAL_CONFIG.get("DB_PASSWORD");
	static private final String DB_IPADRRES = GLOBAL_CONFIG.get("DB_IPADRRES");
	static private final String DB_DATABASE_NAME = GLOBAL_CONFIG.get("DB_DATABASE_NAME");

	private static String url = String.format("jdbc:mysql://%s:3306/%s",
			DB_IPADRRES, DB_DATABASE_NAME);
	
	static {
		try {
			Class.forName(driverString);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.catching(e);
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
		} catch (Exception e) {
			logger.catching(e);
			return null;
		}
	}

	public static List<Map<String, Object>> executeQuery(String sql, Object[] params) {		
		Connection con = getConnection();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				ResultSetMetaData meta = rs.getMetaData();
				int no = meta.getColumnCount();
				for (int i = 1; i <= no; i++) {
					map.put(meta.getColumnName(i), rs.getObject(i));
				}
				res.add(map);
			}
		} catch (SQLException e) {
			logger.catching(e);
		}
		return res;
	}

	public static boolean execute(String sql, Object[] params) {
		Connection con = getConnection();
		List<Map> res = new ArrayList<Map>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			return ps.execute(); 
		} catch (SQLException e) {
			logger.catching(e);
			return false;
		}
	}

	public static void main(String[] args) {

		DBHelper db = new DBHelper();

		Connection c = db.getConnection();
		String sql = "select * from user";
		List list = executeQuery(sql, new Object[] { });
		for (Object object : list) {
			System.out.println(object);
		} 
	}
}
