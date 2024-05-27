package com.trioshop;

import com.trioshop.model.dto.user.UserInfoBySession;
import jakarta.servlet.http.HttpSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.trioshop.repository.mybatis")
public class TrioshopApplication {
	public static void main(String[] args) {
		SpringApplication.run(TrioshopApplication.class, args);
	}


}
