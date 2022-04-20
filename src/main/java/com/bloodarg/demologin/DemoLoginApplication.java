package com.bloodarg.demologin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoLoginApplication.class, args);
	}
	
	@GetMapping("/login")
	public String hello(@RequestParam(value = "name") String name) {
		
	return String.format("Hello %s!", name);
	}


}