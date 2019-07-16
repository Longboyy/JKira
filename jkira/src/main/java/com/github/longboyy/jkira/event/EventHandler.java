package com.github.longboyy.jkira.event;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.longboyy.jkira.KiraClient;
import com.github.longboyy.jkira.event.events.AuthFailEvent;
import com.github.longboyy.jkira.event.events.AuthSuccessEvent;
import com.github.longboyy.jkira.event.events.GroupMessageEvent;
import com.github.longboyy.jkira.event.events.SnitchAlertEvent;
import com.github.longboyy.jkira.model.GroupMessage;
import com.github.longboyy.jkira.model.Token;
import com.github.longboyy.jkira.model.snitch.SnitchAlert;

public class EventHandler {

	private final KiraClient client;
	
	private Set<EventListener> listeners = new HashSet<>();
	
	public EventHandler(KiraClient client) {
		this.client = client;
	}
	
	public void registerListener(EventListener listener) {
		this.listeners.add(listener);
	}
	
	public void unregisterListener(EventListener listener) {
		this.listeners.remove(listener);
	}
	
	public void handle(JSONObject object) {
		String packetType = object.getString("type");
		
		switch(packetType) {
			case "auth":
				boolean valid = object.getBoolean("valid");
				
				
				if(valid) {
					Token token = new Token(object);
					
					AuthSuccessEvent event = new AuthSuccessEvent(client, token);
					for(EventListener listener : listeners) {
						listener.onAuthSuccess(event);
					}
				}else {
					AuthFailEvent event = new AuthFailEvent(client);
					for(EventListener listener : listeners) {
						listener.onAuthFail(event);
					}
				}
				
				break;
			case "data":
				JSONArray groupMessages = object.getJSONArray("snitch-alerts");
				
				for(int i=0; i<groupMessages.length(); i++) {
					JSONObject obj = groupMessages.getJSONObject(i);
					
					GroupMessage msg = new GroupMessage(obj);
					
					GroupMessageEvent event = new GroupMessageEvent(client, msg);
					for(EventListener listener : listeners) {
						listener.onGroupMessage(event);
					}
				}
				
				JSONArray snitchAlerts = object.getJSONArray("snitch-alerts");
				
				for(int i=0; i<snitchAlerts.length(); i++) {
					JSONObject obj = snitchAlerts.getJSONObject(i);
					
					SnitchAlert alert = new SnitchAlert(obj);
					
					SnitchAlertEvent event = new SnitchAlertEvent(client, alert);
					
					for(EventListener listener : listeners) {
						listener.onSnitchAlert(event);
					}
				}
				
				break;
		}
	}
	
}
