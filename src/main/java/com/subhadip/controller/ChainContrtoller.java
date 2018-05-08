package com.subhadip.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChainContrtoller {

	@RequestMapping("/")
	public String index() {
		return "Hello from SpringBoot";
	}
}
