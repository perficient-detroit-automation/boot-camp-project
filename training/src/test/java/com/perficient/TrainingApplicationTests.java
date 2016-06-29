package com.perficient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.perficient.spring.web.TrainingApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TrainingApplication.class)
@WebAppConfiguration
public class TrainingApplicationTests {

	@Test
	public void contextLoads() {
	}

}
