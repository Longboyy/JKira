package com.github.longboyy.jkira.event;

import com.github.longboyy.jkira.KiraClient;

public abstract class Event {

	protected final KiraClient client;
	
	public Event(KiraClient kiraClient) {
		this.client = kiraClient;
	}
	
	public KiraClient getClient() {
		return client;
	}
	
}
