package com.deviceMonitor.init;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.deviceMonitor.impl.service.RemoveVerCodeService;

public class InitVerCodeService implements InitializingBean {
	private static final Logger LOGGER = Logger.getLogger(InitVerCodeService.class);
	
	public void initMethod() {
		try {
			new Thread(RemoveVerCodeService.getInstance()).start();
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
}
