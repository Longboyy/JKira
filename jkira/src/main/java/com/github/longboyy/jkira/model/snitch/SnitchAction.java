package com.github.longboyy.jkira.model.snitch;

public enum SnitchAction {

	ENTER("ENTER"),
	LOGIN("LOGIN"),
	LOGOUT("LOGOUT");
	
	private String id;
	
	SnitchAction(String id) {
		this.id = id;
	}
	
	public static SnitchAction getById(String id) {
		for(SnitchAction action : values()) {
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
