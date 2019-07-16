package com.github.longboyy.jkira.model.snitch;

public enum SnitchType {

	LOGGING("LOGGING"),
	ENTRY("ENTRY");
	
	private String id;
	
	SnitchType(String id) {
		this.id = id;
	}
	
	public static SnitchType getById(String id) {
		for(SnitchType action : values()) {
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
