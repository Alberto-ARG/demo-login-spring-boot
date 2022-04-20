package com.bloodarg.router;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class LoginRouter {


    @GetMapping("/login")
	public String hello(@RequestParam(value = "name") String name) {
		
	return String.format("Hello %s!", name);
	}
}
