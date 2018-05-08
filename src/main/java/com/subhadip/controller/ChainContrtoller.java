package com.subhadip.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.subhadip.model.Device;

@RestController
public class ChainContrtoller {

	@RequestMapping(value="/device", method=RequestMethod.POST)
	public String createDeviec(@RequestBody Device device) {
		return "Hello from SpringBoot";
	}
}
