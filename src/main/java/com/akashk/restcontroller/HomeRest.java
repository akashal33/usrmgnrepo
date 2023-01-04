package com.akashk.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRest {
	
	@GetMapping("/home")
	public String welcome() {
		return " welcome user";
	}

}
