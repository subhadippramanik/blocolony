package com.blocolony.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;

@Component
public class Chain {

	List<Block> blocks = new ArrayList<>();

	public List<Block> getBlocks() {
		return ImmutableList.copyOf(blocks);
	}

	public void push(Block block) {
		blocks.add(block);
	}
}
