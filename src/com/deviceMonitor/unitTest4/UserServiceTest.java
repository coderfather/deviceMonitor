package com.deviceMonitor.unitTest4;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deviceMonitor.intf.service.IBaseService;
import com.deviceMonitor.intf.service.IUserNewService;
import com.deviceMonitor.model.User;

public class UserServiceTest extends BaseTransactionalTest {

	@Autowired
	private IUserNewService<User, String> userNewService;
	
	@Autowired
	private IBaseService<User, String> baseService;
	
	@Test
	public void test() {
		User user1 = userNewService.select("1");
		System.out.println(user1.getUser_name());
		assertNotNull(user1);
		
		User user2 = userNewService.selectByLoginName("user2");
		System.out.println(user2.getUser_name());
		assertNotNull(user2);
		
		User user3 = baseService.select("3");
		System.out.println(user3.getUser_name());
		assertNotNull(user3);
	}
}
