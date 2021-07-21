package com.ibs.litmus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
	Logger log = LoggerFactory.getLogger( HelloController.class);
	@RequestMapping("/hello")
	public String hello() {
		log.info("client has made a request for hello page");
		String response = "Hello World";
		log.debug("Response {}", response);
		return response;
	}
}
