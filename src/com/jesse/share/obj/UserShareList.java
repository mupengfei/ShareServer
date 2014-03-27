package com.jesse.share.obj;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.jesse.share.config.GlobalConfig;
import com.jesse.share.utils.DBHelper;

public class UserShareList {
	private final Map<String, CopyOnWriteArrayList<String>> USER_SHARE_MAP;
	private final static GlobalConfig config = GlobalConfig.getInstance();
	
	private UserShareList() {
		USER_SHARE_MAP = new HashMap<String, CopyOnWriteArrayList<String>>();
		List<Map<String, Object>> rels = DBHelper.executeQuery(config.getSql("SELECT_SHARE_USER_REL"), new Object[]{});
		for (Map<String, Object> map : rels) {
//			 CopyOnWriteArrayList<String> list = USER_SHARE_MAP.get(map.get("user_no"));
			String userNo = (String)map.get("user_no");
			String shareNo = (String)map.get("share_no");
			 if(!USER_SHARE_MAP.containsKey(userNo)){
				 CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
				 list.add(shareNo);
				 USER_SHARE_MAP.put(userNo, list);
			 }else {
				 USER_SHARE_MAP.get(userNo).add(shareNo);
			 }
		}
	}

	static class UserShareListHolder {
		private static UserShareList instance = new UserShareList();
	}

	public static UserShareList getInstance() {
		return UserShareList.UserShareListHolder.instance;
	}

	public Iterator<String> getUserIterator() {
		return USER_SHARE_MAP.keySet().iterator();
	}

	public Iterator<String> getShareIterator(String userName) {
		return USER_SHARE_MAP.get(userName).iterator();
	}

	public void addShare(String userName, String shareNo) {
		CopyOnWriteArrayList<String> shareList = USER_SHARE_MAP.get(userName);
		shareList.add(shareNo);
	}

	public void removeShare(String userName, String shareNo) {
		CopyOnWriteArrayList<String> shareList = USER_SHARE_MAP.get(userName);
		for (String string : shareList) {
			if (shareNo.equals(string)) {
				shareList.remove(string);
				break;
			}
		}
	}
}
