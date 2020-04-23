package com.lyt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class MyShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyShopApplication.class, args);
	}

}
