package com.example.springboot.monitoring.service;

import org.springframework.stereotype.Service;

import io.micrometer.core.annotation.Timed;

@Service
public class HelloService {
	@Timed(value = "hello_foo_service")
	public String helloFoo() {
		return "Hello foo!";
	}
	
	@Timed(value = "hello_bar_service")
	public String helloBar() {
		return "Hello bar!";
	}
}
