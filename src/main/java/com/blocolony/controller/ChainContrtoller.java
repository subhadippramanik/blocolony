package com.blocolony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blocolony.model.Block;
import com.blocolony.model.Device;
import com.blocolony.service.ChainService;
import com.blocolony.service.DeviceService;

@RestController
public class ChainContrtoller {

	@Autowired
	ChainService chainService;
	@Autowired
	DeviceService deviceService;
	
	@RequestMapping(value="/device", method=RequestMethod.POST)
	public HttpStatus createDeviec(@RequestBody Device device) {
		deviceService.createAndRegisterDevice(device);
		return HttpStatus.ACCEPTED;
	}
	
	@RequestMapping(value="/chain", method=RequestMethod.GET)
	public ResponseEntity<List<Block>> getChain() {
		return new ResponseEntity<List<Block>>(chainService.getBlocks(), HttpStatus.OK);
	}
}