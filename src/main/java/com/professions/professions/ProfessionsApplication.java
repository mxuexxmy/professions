package com.professions.professions;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.professions.professions.mapper")
public class ProfessionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfessionsApplication.class, args);
	}

}
