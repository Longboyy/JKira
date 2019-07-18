package com.github.longboyy.jkira.model.snitch;

import org.json.JSONObject;

public class Snitch {

	private Location location;
	private String name;
	private String groupName;
	private SnitchType type;
	
	public Snitch(JSONObject object) {
		this.location = new Location(object.getJSONObject("location"));
		this.name = object.getString("name");
		this.groupName = object.getString("group");
		this.type = SnitchType.getById(object.getString("type"));
	}
	
	public Snitch(Location location, String name, String groupName, SnitchType type) {
		this.location = location;
		this.name = name;
		this.groupName = groupName;
		this.type = type;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public String getName() {
		return name;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public SnitchType getType() {
		return type;
	}
	
	
	
}
