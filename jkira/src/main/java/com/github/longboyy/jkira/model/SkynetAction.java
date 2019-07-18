package com.github.longboyy.jkira.model;

public enum SkynetAction {

	LOGIN("LOGIN"),
	LOGOUT("LOGOUT");
	
	private String id;
	
	SkynetAction(String id) {
		this.id = id;
	}
	
	public static SkynetAction getById(String id) {
		for(SkynetAction action : values()) {
			if(id.equalsIgnoreCase(action.getId())) {
				return action;
			}
		}
		
		return null;
	}
	
	public String getId() {
		return id;
	}
}
