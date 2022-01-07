package com.stackabuse.multitenantnosqlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.stackabuse.multitenantnosqlservice")
@SpringBootApplication
public class MultitenantNosqlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultitenantNosqlServiceApplication.class, args);
	}

}
