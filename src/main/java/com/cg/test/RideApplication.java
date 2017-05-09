package com.cg.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.cg.test")
public class RideApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RideApplication.class, args);
	}

}
