package com.blocolony.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.blocolony.model.Block;
import com.blocolony.model.Device;

@Service
public class BlockService {

	public Block createBlock(Instant now, Device device) {		
		return new Block(now, device);
	}

}
