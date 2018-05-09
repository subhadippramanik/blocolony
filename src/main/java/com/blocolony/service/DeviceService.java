package com.blocolony.service;

import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blocolony.model.Block;
import com.blocolony.model.Device;

@Service
public class DeviceService {

	@Autowired
	private ChainService chainService;
	
	private ExecutorService executor = Executors.newFixedThreadPool(4);

	public void createAndRegisterDevice(Device device) {
		final Block block = new Block(Instant.now(), createDevice(device.getId()));
		
		executor.submit(new Callable<Void>() {
			public Void call() throws Exception {
				chainService.addBlock(block);
				return null;
			}
		});
	}

	private Device createDevice(String id) {
		Device device = new Device(id);
		return device;
	}

}
