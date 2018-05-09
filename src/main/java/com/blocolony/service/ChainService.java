package com.blocolony.service;

import java.time.Instant;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blocolony.model.Block;
import com.blocolony.model.Chain;

@Service
public class ChainService {
	
	int difficulty = 1;

	@Autowired
	private Chain chain;
	
	@Autowired
	private BlockService blockService;
	
	@PostConstruct
	public void initializeChain() {
		createGenesisBlock();
	}
	
	private void createGenesisBlock() {
		Block block = blockService.createBlock(Instant.now(), null);
		chain.push(block);
	}

	public List<Block> getBlocks() {		
		return chain.getBlocks();
	}

	public void addBlock(Block block) {		
		List<Block> blocks = getBlocks();
		block.setPreviousHash(blocks.get(blocks.size()-1).getHashCode());
        Block minedBlock = mineBlock(block);
        chain.push(minedBlock);		
	}
	
	private Block mineBlock(Block block) {
        while (!block.getHashCode().startsWith("0")) {
        	block.increaseNonce();
        	block.calculateHash();
        }
        System.out.println("BLOCK MINED: " + block.getHashCode());
        return block;
    }
}
