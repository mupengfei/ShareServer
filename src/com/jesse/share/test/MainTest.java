package com.jesse.share.test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jesse.share.obj.ShareObj;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShareObj sb = new ShareObj("600687", "贵研铂业", 10.29, 12.19, new Date());
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, sb);
			System.out.println(sw);
			ShareObj ss = mapper.readValue(sw.toString(), ShareObj.class);
			System.out.println(ss.getSharename());
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
