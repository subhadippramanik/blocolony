package com.blocolony.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blocolony.model.Block;
import com.blocolony.model.Chain;

@Service
public class ChainService {

	private final int difficultyFactor = 5;
	
	private String difficultySalt;

	@Autowired
	private Chain chain;

	@Autowired
	private BlockService blockService;

	@PostConstruct
	public void initializeChain() {
		createDifficultySalt();
		createGenesisBlock();		
	}

	public List<Block> getBlocks() {
		return chain.getBlocks();
	}
	
	public Boolean hasDeviceWithSameId(String id) {
		return getBlocks().stream()
				.filter(block -> Objects.nonNull(block.getDevice()) && block.getDevice().getId().equals(id)).findFirst()
				.isPresent();
	}

	public void addBlock(Block block) {
		List<Block> blocks = getBlocks();
		block.setPreviousHash(blocks.get(blocks.size() - 1).getHashCode());
		Block minedBlock = mineBlock(block);
		chain.push(minedBlock);
	}
	
	private void createGenesisBlock() {
		Block block = blockService.createBlock(Instant.now(), null);
		chain.push(block);
	}
	
	private Block mineBlock(Block block) {
		while (!block.getHashCode().startsWith(difficultySalt)) {
			block.increaseNonce();
			block.calculateHash();
		}
		System.out.println("BLOCK MINED: " + block.getHashCode());
		return block;
	}

	private void createDifficultySalt() {
		char[] chars = new char[difficultyFactor];
		Arrays.fill(chars, '0');
		difficultySalt = new String(chars);		
	}
}
