package com.jesse.share.test;

import java.io.File;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println( GlobalConfig.getInstance().get("test"));
		File file = new File("/");
		System.out.println(MainTest.class.getResource("/").getPath());
	}

}
