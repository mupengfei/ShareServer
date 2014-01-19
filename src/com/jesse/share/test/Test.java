package com.jesse.share.test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL url = new URL(
					"http://hq.sinajs.cn/rn=1390043701224&list=sz000078,bk_new_sybh");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.addRequestProperty(
//					"User-Agent",
//					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36 CoolNovo/2.0.9.20");
			// con.connect();
			InputStream is = con.getInputStream();
			System.out.println(con.getResponseCode());
			System.out.println(con.getResponseMessage());
			// Map<String, List<String>> map = con.getHeaderFields();
			// Set<Map.Entry<String, List<String>>> set = map.entrySet();
			// for (Entry<String, List<String>> entry : set) {
			// System.out.println(entry.getKey());
			// System.out.println(entry.getValue().get(0));
			// }
			// BufferedReader br = new java.io.BufferedReader(
			// new java.io.InputStreamReader(is));
			// System.out.println(br.readLine());
			// br.close();
			ByteBuffer bb = ByteBuffer.allocate(374);
			ReadableByteChannel cha = Channels.newChannel(is);
			cha.read(bb);
			bb.flip();
			System.out.println(decode(bb));
			cha.close();
			// System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
