package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DockerSampleProj01Application {

	public static void main(String[] args) {
		SpringApplication.run(DockerSampleProj01Application.class, args);
		System.out.println(System.currentTimeMillis()+"starts here");
	}

}
