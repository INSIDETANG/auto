package com.autonomy;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@MapperScan(value= {"com.autonomy.dao","com.autonomy.dao.ex"})	//扫描mapper
public class AutonomyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutonomyApplication.class, args);
	}

}
