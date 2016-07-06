package com.perficient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.perficient.spring.web.EmployeeApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EmployeeApplication.class)
@WebAppConfiguration
public class EmployeeApplicationTests {

	@Test
	public void contextLoads() {
	}

}
