package com.lyt.dao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootTest
class LytDaoApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("-------------------------------");
		System.err.println("测试lyt-dao！！！！");
		System.out.println("-------------------------------");
	}

}
