package com.autonomy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class AutonomyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutonomyApplication.class, args);
	}

}
