package com.jesse.share.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jesse.share.config.GlobalConfig;
import com.jesse.share.obj.ShareObj;

public class ShareGetting {
	/**
	 * @param args
	 */
	public static ShareObj getShareFromServer(String shareNo) {
		try {
			URL url = new URL(GlobalConfig.getInstance().get("SINA_SHARE_SERVER_URL") + shareNo);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			InputStream is = con.getInputStream();
			ByteBuffer bb = ByteBuffer.allocate(1024);
			ReadableByteChannel cha = Channels.newChannel(is);
			cha.read(bb);
			bb.flip();
			String str = decode(bb);
			cha.close();
			return str2obj(str, shareNo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decode(ByteBuffer buffer) {
		Charset charset = null;
		CharsetDecoder decoder = null;
		CharBuffer charBuffer = null;
		try {
			charset = Charset.forName("gb2312");
			decoder = charset.newDecoder();
			charBuffer = decoder.decode(buffer);
			return charBuffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
	
	public static ShareObj str2obj(String str, String shareNo){
		int st = str.indexOf("\"");
		int ed = str.indexOf("\"", st + 1);
		if(ed - st == 1){
			return null;
		}
		str = str.substring(st + 1, ed);
		String[] arr = str.split(",");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowTime = null;
		try {
			nowTime = sdf.parse(arr[arr.length-3] + " " + arr[arr.length-2]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ShareObj(shareNo, arr[0], Double.parseDouble(arr[3]), Double.parseDouble(arr[2]), nowTime);
	}
}
