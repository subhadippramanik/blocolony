package com.blocolony.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.blocolony.model.Block;
import com.blocolony.model.Chain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChainService {

	@Value("${difficulty.factor}")
	private int difficultyFactor;
	
	private String difficultySalt;
	
	private Set<String> queuedDevices = new HashSet<>();
	
	private ExecutorService executor = Executors.newFixedThreadPool(4);

	@Autowired
	private Chain chain;

	@Autowired
	private BlockService blockService;
	
	@Autowired
	private MessageService messageService;

	@PostConstruct
	public void initializeChain() {
		createDifficultySalt();
		createGenesisBlock();		
	}

	public List<Block> getBlocks() {
		return chain.getBlocks();
	}
	
	public Boolean hasAnyDeviceWithSameId(String id) {
		return getBlocks().stream()
				.filter(block -> Objects.nonNull(block.getDevice()) && block.getDevice().getId().equals(id)).findFirst()
				.isPresent() || queuedDevices.contains(id);
	}

	public void addBlock(Block block) throws InterruptedException, ExecutionException {
		queuedDevices.add(block.getDevice().getId());
		List<Block> blocks = getBlocks();
		block.setPreviousHash(blocks.get(blocks.size() - 1).getHashCode());
		executor.submit(()->{
			Block minedBlock =  mineBlock(block);
			chain.push(minedBlock);
			try {
				messageService.publish(new ObjectMapper().writeValueAsString(block));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			queuedDevices.remove(block.getDevice().getId());
		});		
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
		Logger.getLogger(getClass()).info("Block mined: " + block.getHashCode());
		return block;
	}

	private void createDifficultySalt() {
		char[] chars = new char[difficultyFactor];
		Arrays.fill(chars, '0');
		difficultySalt = new String(chars);		
	}
}
