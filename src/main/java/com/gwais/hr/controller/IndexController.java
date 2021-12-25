package com.gwais.hr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {
	
	/*
	 * Returning static HTML pages/views
	 */
	
	@GetMapping(value = {"","/","/index","index.html"})
	public ModelAndView indexPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}
}
