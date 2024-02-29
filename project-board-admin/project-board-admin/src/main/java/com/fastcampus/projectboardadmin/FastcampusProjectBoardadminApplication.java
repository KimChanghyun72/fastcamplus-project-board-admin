package com.fastcampus.projectboardadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class FastcampusProjectBoardadminApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastcampusProjectBoardadminApplication.class, args);
	}

}
