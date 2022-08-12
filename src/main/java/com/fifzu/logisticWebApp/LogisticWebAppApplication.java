package com.fifzu.logisticWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LogisticWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticWebAppApplication.class, args);
	}

	//Name of request: -> mapped get requests to http://localhost:8080/hello
	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}
