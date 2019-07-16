package com.github.longboyy.jkira.event.events;

import com.github.longboyy.jkira.KiraClient;
import com.github.longboyy.jkira.event.Event;

public class AuthFailEvent extends Event {
	
	public AuthFailEvent(KiraClient kiraClient) {
		super(kiraClient);
	}

}
