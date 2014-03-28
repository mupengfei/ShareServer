package com.jesse.share.obj;

import java.util.Date;

public class ShareObj {
	private String shareNo;
	private String sharename;
	private double nowPrice;
	private double yesterdayPrice;
	private Date nowTime;
	
	private ShareObj(){
		
	}
	
	public ShareObj(String shareNo, String sharename, double nowPrice, double yesterdayPrice,
			Date nowTime) {
		super();
		this.shareNo = shareNo;
		this.sharename = sharename;
		this.nowPrice = nowPrice;
		this.yesterdayPrice = yesterdayPrice;
		this.nowTime = nowTime;
	}
	
	public double getYesterdayPrice() {
		return yesterdayPrice;
	}

	public void setYesterdayPrice(double yesterdayPrice) {
		this.yesterdayPrice = yesterdayPrice;
	}

	public String toString(){
		return this.shareNo + "|" + this.sharename + "|" + this.nowPrice + "|"+ this.yesterdayPrice + "|" + this.nowTime;
	}

	public String getShareNo() {
		return shareNo;
	}

	public void setShareNo(String shareNo) {
		this.shareNo = shareNo;
	}

	public String getSharename() {
		return sharename;
	}

	public void setSharename(String sharename) {
		this.sharename = sharename;
	}

	public double getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(double nowPrice) {
		this.nowPrice = nowPrice;
	}

	public Date getNowTime() {
		return nowTime;
	}

	public void setNowTime(Date nowTime) {
		this.nowTime = nowTime;
	}

}
