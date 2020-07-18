package com.example.postrbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
public class PostrBackend {

	public static void main(String[] args) {
		SpringApplication.run(PostrBackend.class, args);
		

	}

}
