package com.deviceMonitor.impl.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.deviceMonitor.model.VerificationCode;

public class RemoveVerCodeService implements Runnable {
	private static final Logger LOGGER = Logger.getLogger(RemoveVerCodeService.class);
	private static Set<VerificationCode> setVCode = new HashSet<VerificationCode>();
	
	private static RemoveVerCodeService instance = null;
	
	public static synchronized RemoveVerCodeService getInstance() {
		if (instance == null) {
			instance = new RemoveVerCodeService();
		}
		
		return instance;
	}
	
	public Set<VerificationCode> getSetVCode() {
		return setVCode;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Iterator<VerificationCode> it = setVCode.iterator();  
				
				while (it.hasNext()) {
					VerificationCode tbVerificationCode = (VerificationCode) it.next();
					
					long timeDiff = Calendar.getInstance().getTimeInMillis() - tbVerificationCode.getCreateTime().getTime();
					if (timeDiff > 3 * 60 * 1000) {
						it.remove();
					}
				}
			} catch (Exception e) {
				LOGGER.error(e);
			} finally {
				try {
					Thread.sleep(4 * 60 * 60 * 1000);
				} catch (InterruptedException e) {
					LOGGER.error(e);
				}
			}
		}
	}
}
