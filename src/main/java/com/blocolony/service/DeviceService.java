package com.blocolony.service;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blocolony.model.Block;
import com.blocolony.model.Device;

@Service
public class DeviceService {

	@Autowired
	private ChainService chainService;
	
	public void createAndRegisterDevice(Device device) {
	    final Block block = new Block(Instant.now(), createDevice(device.getId()));
	    CompletableFuture<Void>.supplyAsync(()->chainService.addBlock(block));
	}

	private Device createDevice(String id) {
		Device device = new Device(id);
		return device;
	}

	
}
