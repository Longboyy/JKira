package com.github.longboyy.jkira.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Token {

	private long expireTime;
	private String[] chatGroups;
	private String[] snitchGroups;
	private boolean skynetEnabled;
	
	public Token(JSONObject object) {
		this.expireTime = object.getLong("expires");
		
		List<String> chatGroups = new ArrayList<>();
		for(Object groupName : object.getJSONArray("chats")) {
			chatGroups.add((String)groupName);
		}
		
		List<String> snitchGroups = new ArrayList<>();
		for(Object groupName : object.getJSONArray("snitches")) {
			snitchGroups.add((String)groupName);
		}
		
		this.chatGroups = chatGroups.toArray(new String[0]);
		this.snitchGroups = snitchGroups.toArray(new String[0]);
		this.skynetEnabled = object.getBoolean("skynet");
	}
	
	public boolean isValid() {
		return expireTime == -1 || expireTime-System.currentTimeMillis() <= 0;
	}
	
	public long getExpireTime() {
		return expireTime;
	}
	
	public String[] getChatGroups() {
		return chatGroups;
	}
	
	public String[] getSnitchGroups() {
		return snitchGroups;
	}
	
	public boolean isSkynetEnabled() {
		return skynetEnabled;
	}
	
}
