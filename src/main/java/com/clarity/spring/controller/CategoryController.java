package com.clarity.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

	@GetMapping("/categories")
	public String categories() {
		
		return "public/categories";
	}
}
