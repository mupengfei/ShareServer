package com.jesse.share.task;

import java.util.Calendar;

public class TaskOfGettingShare implements Runnable {
	public void run() {
		Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		
	}
}
