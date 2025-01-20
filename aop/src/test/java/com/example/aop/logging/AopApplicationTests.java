package com.example.aop.logging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.aop.AopApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootTest
class AopApplicationTests {

	@Autowired
	private GreetingService greetingService;

	@Test
	void greetingServiceTest() {
		String result = greetingService.greet("eun2ce");
		assertNotNull(result);
		assertEquals("Hello eun2ce", result);
	}

}
