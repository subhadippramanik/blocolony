package com.subhadip.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.subhadip")
@EntityScan("com.subhadip")

public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
