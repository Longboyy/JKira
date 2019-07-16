package com.github.longboyy.jkira.model.snitch;

import org.json.JSONObject;

public class Location {

	private int x, y, z;
	private String world;
	
	public Location(JSONObject object) {
		this.x = object.getInt("x");
		this.y = object.getInt("y");
		this.z = object.getInt("z");
		this.world = object.getString("world");
	}
	
	public Location(int x, int y, int z, String world) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
	public String getWorld() {
		return world;
	}
	
}
