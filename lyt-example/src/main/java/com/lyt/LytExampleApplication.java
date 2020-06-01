package com.lyt;

import com.lyt.property.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
// 此注解表示动态扫描DAO接口所在包，实际上不加下面这条语句也可以找到
@MapperScan("com.lyt.module.*.dao")

// 开启ConfigurationProperties功能
@EnableConfigurationProperties({
	FileProperties.class
})

// 此注解表示SpringBoot启动类
@SpringBootApplication
public class LytExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LytExampleApplication.class, args);
	}

}
