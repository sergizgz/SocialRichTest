package com.socialrich.socialrich;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SocialrichApplicationTests {

	@Test
	void contextLoads() {
		String operando= "1";
		assertEquals("1",operando);

	}

}
