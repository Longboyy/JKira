package com.github.longboyy.jkira.event.events;

import com.github.longboyy.jkira.KiraClient;
import com.github.longboyy.jkira.event.Event;
import com.github.longboyy.jkira.model.Token;

public class AuthSuccessEvent extends Event {

	private final Token token;
	
	public AuthSuccessEvent(KiraClient kiraClient, Token token) {
		super(kiraClient);
		this.token = token;
	}
	
	public Token getToken() {
		return token;
	}

}
