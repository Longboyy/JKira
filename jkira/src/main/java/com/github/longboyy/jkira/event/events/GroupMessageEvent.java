package com.github.longboyy.jkira.event.events;

import com.github.longboyy.jkira.KiraClient;
import com.github.longboyy.jkira.event.Event;
import com.github.longboyy.jkira.model.GroupMessage;

public class GroupMessageEvent extends Event {

	private GroupMessage message;
	
	public GroupMessageEvent(KiraClient kiraClient, GroupMessage message) {
		super(kiraClient);
		this.message = message;
	}
	
	public GroupMessage getMessage() {
		return message;
	}

}
