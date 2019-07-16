package com.github.longboyy.jkira.model;

import org.json.JSONObject;

public class GroupMessage {

	private long time;
	private String groupName;
	private String player;
	private String message;
	
	public GroupMessage(JSONObject object) {
		this.time = object.getLong("time");
		this.groupName = object.getString("group");
		this.player = object.getString("player");
		this.message = object.getString("message");
	}
	
	public long getTime() {
		return time;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public String getMessage() {
		return message;
	}
	
}
