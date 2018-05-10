package com.blocolony.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blocolony.mqtt.MqttMessagePublisher;

@Service
public class MessageService {

	@Autowired
	private MqttMessagePublisher publisher;
	
	private final String TOPIC_BLOCK_ADDED = "down/block/added";
	
	public void publish(String message) {
		publisher.publish(message, TOPIC_BLOCK_ADDED);
	}
	
}
