package com.blocolony.model;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

import com.google.common.hash.Hashing;


public class Block {
	
	long timestamp;
	Device device;
	String previousHash;
	int nonce = 0;
	String hashCode;
	
	public Block(Instant timestamp, Device device) {
		this.timestamp = System.currentTimeMillis();
		this.device = device;
		calculateHash();
	}	
	
	public Device getDevice() {
		return device;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}
	
	public void increaseNonce() {
		nonce++;
	}	
	
	public String getHashCode() {
		return hashCode;
	}

	public void calculateHash() {
		this.hashCode = Hashing.sha256().newHasher().putString(this.previousHash + this.timestamp + this.device + this.nonce, StandardCharsets.UTF_8).hash().toString();				
	}

}
