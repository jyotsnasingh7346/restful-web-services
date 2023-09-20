package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	// @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "hello-world")
	public String helloWorld() {
		return "Hello World!";	// since string is returned, its printed as it is
	}
	
	@GetMapping(path = "hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("This is a hello world bean"); // since bean is returned, its parsed to JSON
	}
	
	@GetMapping(path = "hello-world/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		// since bean is returned, its parsed to JSON
		return new HelloWorldBean(String.format("Hello, its %s's world", name));
	}
	
	@GetMapping(path = "hello-world-i18n")
	public String helloWorldi18n() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
	
}
