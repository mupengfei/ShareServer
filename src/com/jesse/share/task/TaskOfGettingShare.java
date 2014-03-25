package com.jesse.share.task;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jesse.share.obj.UserShareList;

public class TaskOfGettingShare implements Job {
	private Logger logger = LogManager.getLogger(TaskOfGettingShare.class
			.getName());

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		logger.info("开始抓包");
		Iterator<String> userIterator = UserShareList.getInstance()
				.getUserIterator();
		Set<String> tmpSet = new HashSet<String>();
		while (userIterator.hasNext()) {
			String userNo = userIterator.next();
			Iterator<String> shareIterator = UserShareList.getInstance()
					.getShareIterator(userNo);
			while (shareIterator.hasNext()) {
				String shareNo = shareIterator.next();
				if (!tmpSet.contains(shareNo)) {
					// TODO
					tmpSet.add(shareNo);
				}
			}
		}
		logger.info("结束抓包");
	}
}
