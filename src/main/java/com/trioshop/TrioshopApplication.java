package com.trioshop;

import com.trioshop.model.dto.user.UserInfoBySession;
import jakarta.servlet.http.HttpSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = "com.trioshop.repository.mybatis")
public class TrioshopApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(TrioshopApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TrioshopApplication.class);
	}
}
