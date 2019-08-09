package com.penapereira.example.constructs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(loader = CustomSpringBootContextLoader.class)
public class JavaPatternsAndConstructsApplicationTests {

	@Test
	public void contextLoads() {
	}

}
