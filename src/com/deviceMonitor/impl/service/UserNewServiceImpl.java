package com.deviceMonitor.impl.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.deviceMonitor.intf.dao.IUserDao;
import com.deviceMonitor.intf.service.IUserNewService;

@SuppressWarnings({"unchecked", "rawtypes"})
@Service
public class UserNewServiceImpl <T, PK> extends BaseServiceImpl implements IUserNewService {

	@Resource
	private IUserDao userDao;

	@Resource
	public void setBaseDao(IUserDao userDao) {
		super.setBaseDao(userDao);
	}

	@Override
	public T selectByLoginName(String loginName) {
		return (T) userDao.selectByLoginName(loginName);
	}
}
