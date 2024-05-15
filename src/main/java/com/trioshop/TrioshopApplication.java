package com.trioshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.trioshop.mybatis")
public class TrioshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrioshopApplication.class, args);
	}

}
