package com.github.longboyy.jkira;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import com.github.longboyy.jkira.event.EventHandler;
import com.github.longboyy.jkira.event.EventListener;

public class KiraClient extends WebSocketClient {
	
	private EventHandler eventHandler;
	
	public KiraClient(URI serverUri) {
		super(serverUri);
		eventHandler = new EventHandler(this);
	}
	
	
	
	public void registerListener(EventListener listener) {
		this.eventHandler.registerListener(listener);
	}
	
	public void unregisterListener(EventListener listener) {
		this.eventHandler.unregisterListener(listener);
	}
	
	
	

	@Override
	public void onOpen(ServerHandshake handshakeData) {
		
	}

	@Override
	public void onMessage(String message) {
		JSONObject object = new JSONObject(message);
		eventHandler.handle(object);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		
	}

	@Override
	public void onError(Exception ex) {
		
	}
	
}
