package com.niveus.CSVLoader.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csvloader")
public class CSVLoaderController {


	@GetMapping("/loadcsv")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	
}
