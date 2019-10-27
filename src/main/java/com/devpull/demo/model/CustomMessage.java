package com.devpull.demo.model;

public class CustomMessage {

	private int id;

	private int receiverId;

	private String receiverUsername;
	
	private int senderId;

	private String senderUsername;
	
	private String text;
		
	public CustomMessage() {
		
	}

	public CustomMessage(String receiverUsername, String senderUsername, String text) {
		this.receiverUsername = receiverUsername;
		this.senderUsername = senderUsername;
		this.text = text;
	}

	public CustomMessage(int id, int receiverId, int senderId, String text) {
		this.id = id;
		this.receiverId = receiverId;
		this.senderId = senderId;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getReceiverUsername() {
		return receiverUsername;
	}

	public void setReceiverUsername(String receiverUsername) {
		this.receiverUsername = receiverUsername;
	}

	public String getSenderUsername() {
		return senderUsername;
	}

	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}

	@Override
	public String toString() {
		return "CustomMessage [id=" + id + ", receiverId=" + receiverId + ", senderId=" + senderId + ", text=" + text
				+ "]";
	}
	
	
}
