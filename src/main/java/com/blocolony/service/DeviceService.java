package com.blocolony.service;

import java.time.Instant;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blocolony.model.Block;
import com.blocolony.model.Device;

@Service
public class DeviceService {

	@Autowired
	private ChainService chainService;

	public void createAndRegisterDevice(Device device) throws InterruptedException, ExecutionException {
		Device createdDevice = createDevice(device.getId());
		final Block block = new Block(Instant.now(), createdDevice);
		chainService.addBlock(block);
	}

	private Device createDevice(String id) {
		Device device = new Device(id);
		return device;
	}

}
