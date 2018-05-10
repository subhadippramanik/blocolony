package com.blocolony.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.springframework.stereotype.Component;

@Component
public class MqttMessagePublisher {
	private int qos = 1;
	private String broker = "tcp://localhost:1883";
	private final String clientId = MqttClient.generateClientId();
	
	public void publish(String messageBody, String topic) {
		try {
			MqttClient client = new MqttClient(broker, clientId);
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setQos(qos);
			message.setPayload(messageBody.getBytes());
			client.publish(topic, message);
			client.disconnect();
		} catch (MqttSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
