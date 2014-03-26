package com.jesse.share.obj;
 
import com.jesse.share.config.GlobalConfig;
import com.jesse.share.utils.DBHelper;

public class ShareUtil {
	public static void insertShareInfo(ShareObj share) {
		DBHelper.execute(
				GlobalConfig.getInstance().getSql("INSERT_SHARE_INFO"),
				new Object[] { share.getShareNo(), share.getSharename(),
						share.getNowPrice(), share.getNowTime() });
	}
}
