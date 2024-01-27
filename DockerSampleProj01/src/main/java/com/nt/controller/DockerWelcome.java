package com.nt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerWelcome {
   
	@GetMapping("/welcome")
	public String getWelcome()
	{
		return "<h1>Welcome to Docker tool</h1>";
	}
}
