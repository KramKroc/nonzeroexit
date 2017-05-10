package com.kramkroc;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleServiceApplication.class)
@WebAppConfiguration
public class SampleServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
