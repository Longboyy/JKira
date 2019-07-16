package com.github.longboyy.jkira.model.snitch;

import org.json.JSONObject;

public class SnitchAlert {

	private long alertTime;
	private String player;
	private SnitchAction action;
	private Snitch snitch;
	
	public SnitchAlert(JSONObject object) {
		this.alertTime = object.getLong("time");
		this.player = object.getString("player");
		this.action = SnitchAction.getById(object.getString("action"));
		JSONObject snitchObj = object.getJSONObject("snitch");
		snitch = new Snitch(snitchObj);
	}
	
	public SnitchAlert(long alertTime, String player, SnitchAction action, Snitch snitch) {
		this.alertTime = alertTime;
		this.player = player;
		this.action = action;
		this.snitch = snitch;
	}
	
	public long getTime() {
		return alertTime;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public SnitchAction getAction() {
		return action;
	}
	
	public Snitch getSnitch() {
		return snitch;
	}
	
}
