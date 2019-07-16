package com.github.longboyy.jkira.event;

import com.github.longboyy.jkira.event.events.AuthFailEvent;
import com.github.longboyy.jkira.event.events.AuthSuccessEvent;
import com.github.longboyy.jkira.event.events.GroupMessageEvent;
import com.github.longboyy.jkira.event.events.SnitchAlertEvent;

public abstract class EventListener {
	
	public void onAuthSuccess(AuthSuccessEvent event) {}
	public void onAuthFail(AuthFailEvent event) {}
	
	public void onSnitchAlert(SnitchAlertEvent event) {}
	
	public void onGroupMessage(GroupMessageEvent event) {}
	
}
