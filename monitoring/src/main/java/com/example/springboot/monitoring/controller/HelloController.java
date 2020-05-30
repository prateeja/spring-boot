package com.example.springboot.monitoring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.monitoring.service.HelloService;

@RestController
@RequestMapping("/hello")
public class HelloController {
	@Autowired
	private HelloService helloService;
	
	@GetMapping("/foo")
	public String foo() {
		return helloService.helloFoo();
	}
	
	@GetMapping("/bar")
	public String bar() {
		return helloService.helloBar();
	}
}
