package com.deviceMonitor.unitTest4;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试类基类，默认使用事务回滚
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:springConfig/applicationContext-servlet.xml",
								 "classpath:springConfig/jdbc-context.xml" })
public class BaseTransactionalTest extends AbstractTransactionalJUnit4SpringContextTests {
}
