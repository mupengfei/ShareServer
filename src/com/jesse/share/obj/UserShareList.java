package com.jesse.share.obj;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserShareList {
	private final Map<String, CopyOnWriteArrayList<String>> USER_SHARE_MAP;

	private UserShareList() {
		USER_SHARE_MAP = new HashMap<String, CopyOnWriteArrayList<String>>();
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
