package com.subhadip.model;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class Chain {
	int difficulty = 5;
	List<Block> blocks = ImmutableList.of();

	public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = ImmutableList.copyOf(blocks);
	}
}
