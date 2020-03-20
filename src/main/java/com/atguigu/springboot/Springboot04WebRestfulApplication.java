package com.atguigu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

@EnableAutoConfiguration(exclude = { JpaRepositoriesAutoConfiguration.class })
@SpringBootApplication
public class Springboot04WebRestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot04WebRestfulApplication.class, args);
	}


}