package com.example.MongoProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableCaching(proxyTargetClass = true)
@SpringBootApplication
public class MongoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoProjectApplication.class, args);
	}

}
