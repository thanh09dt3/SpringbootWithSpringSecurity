package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/field")
public class FieldControler {
	
	
	@GetMapping("/index")
	@ResponseBody
	public String index () {
		return "field";
	}
}
