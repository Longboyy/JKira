package com.github.longboyy.jkira;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

import com.github.longboyy.jkira.model.GroupMessage;
import com.github.longboyy.jkira.model.Session;
import com.github.longboyy.jkira.model.SkynetAction;
import com.github.longboyy.jkira.model.Token;
import com.github.longboyy.jkira.model.snitch.SnitchAlert;

public abstract class KiraClient extends WebSocketClient {
	
	private static final String KIRA_VERSION = "1";
	
	private static final String KIRA_SOCKET_ADDRESS = "wss://mc.civclassic.com:14314?apiToken=%s&applicationId=%s&apiVersion="+KIRA_VERSION;
	
	private Token token;
	
	private String applicationId = "";
	
	private KiraClient client;

	public KiraClient(String token, String applicationId) throws URISyntaxException {
		super(new URI(String.format(KIRA_SOCKET_ADDRESS, token, applicationId)));
		this.token = new Token(token, -1);
		this.applicationId = applicationId;
		this.client = this;
	}
	
	private KiraClient(URI serverUri) {
		super(serverUri);
	}
	
	public abstract void onAuthSuccess(Session session);
	public abstract void onAuthFail();
	
	public abstract void onSnitchAlert(SnitchAlert alert);
	
	public abstract void onGroupMessage(GroupMessage message);
	
	public abstract void onNewPlayer(String playerName, long timeStamp);
	
	public abstract void onSkynet(String player, SkynetAction action, long time);
	
	public void requestToken() {
		this.send("{\"type\": \"new-token\"}");
	}
	
	public synchronized void setToken(String token) {
		this.token = new Token(token, -1);
	}

	@Override
	public void onOpen(ServerHandshake handshakeData) {
	}

	@Override
	public void onMessage(String message) {
		//System.out.println(message);
		JSONObject object = new JSONObject(message);
		handle(object);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		this.requestToken();
	}

	@Override
	public void onError(Exception ex) {
	}
	
	private void handle(JSONObject object) {
		String packetType = object.getString("type");
		
		switch(packetType) {
			case "auth":
				boolean valid = object.getBoolean("valid");
				
				
				if(valid) {
					Session session = new Session(object);
					
					this.onAuthSuccess(session);
				}else {
					this.onAuthFail();
				}
				
				break;
			case "new-token":		
				this.token = new Token(object);
				
				break;
			case "data":
				if(object.has("group-messages")) {
					JSONArray groupMessages = object.getJSONArray("group-messages");
					
					for(int i=0; i<groupMessages.length(); i++) {
						JSONObject obj = groupMessages.getJSONObject(i);
						
						GroupMessage msg = new GroupMessage(obj);
						
						this.onGroupMessage(msg);
					}
				}
				
				if(object.has("snitch-alerts")) {
					JSONArray snitchAlerts = object.getJSONArray("snitch-alerts");
					
					for(int i=0; i<snitchAlerts.length(); i++) {
						JSONObject obj = snitchAlerts.getJSONObject(i);
						
						SnitchAlert alert = new SnitchAlert(obj);
						
						this.onSnitchAlert(alert);
					}
				}
				
				if(object.has("skynet")) {
				
					JSONArray skynetPlayers = object.getJSONArray("skynet");
					
					for(int i=0; i<skynetPlayers.length(); i++) {
						JSONObject obj = skynetPlayers.getJSONObject(i);
						
						this.onSkynet(obj.getString("player"), SkynetAction.getById(obj.getString("action")), obj.getLong("time"));
					}
				}
				
				
				if(object.has("new-players")) {
					JSONArray newPlayers = object.getJSONArray("new-players");
					
					for(int i=0; i<newPlayers.length(); i++) {
						JSONObject obj = newPlayers.getJSONObject(i);
						
						this.onNewPlayer(obj.getString("player"), obj.getLong("time"));
					}
				}
				
				break;
		}
	}
	
}
