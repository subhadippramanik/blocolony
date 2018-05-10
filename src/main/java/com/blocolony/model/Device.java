package com.blocolony.model;

import java.util.UUID;

public class Device {

	String id;
	String token;

	public Device() {
		// TODO Auto-generated constructor stub
	}
	
	public Device(String id) {
		this.id = id;
		this.token = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}


}
