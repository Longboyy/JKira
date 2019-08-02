package com.github.longboyy.jkira.model;

import org.json.JSONObject;

public class Token {

	private String secret;
	private long expireTime;
	
	public Token(JSONObject object) {
		secret = object.getString("secret");
		expireTime = object.getLong("expires");
	}
	
	public Token(String secret, long expireTime) {
		this.secret = secret;
		this.expireTime = expireTime;
	}
	
	public String getSecret() {
		return secret;
	}
	
	public boolean isValid() {
		return expireTime == -1 || expireTime-System.currentTimeMillis() <= 0;
	}
	
}
