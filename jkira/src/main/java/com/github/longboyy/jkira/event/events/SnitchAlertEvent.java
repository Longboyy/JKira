package com.github.longboyy.jkira.event.events;

import com.github.longboyy.jkira.KiraClient;
import com.github.longboyy.jkira.event.Event;
import com.github.longboyy.jkira.model.snitch.SnitchAlert;

public class SnitchAlertEvent extends Event {

	private SnitchAlert alert;
	
	public SnitchAlertEvent(KiraClient kiraClient, SnitchAlert alert) {
		super(kiraClient);
		this.alert = alert;
	}
	
	public SnitchAlert getAlert() {
		return alert;
	}

}
