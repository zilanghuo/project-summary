package com.zilanghuo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ProjectSummaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSummaryApplication.class, args);
	}
}
