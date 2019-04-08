package com.devpull.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="receiver_id")
	private int receiverId;
	
	@Column(name="sender_id")
	private int senderId;
	

	@Column(name="when")
	private String dateTime;
	
	@Column(name="text")
	private String msgData;
	
	public Message() {
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

	public String getWhen() {
		return dateTime;
	}

	public void setWhen(String when) {
		this.dateTime = when;
	}

	public String getMsgData() {
		return msgData;
	}

	public void setMsgData(String msgData) {
		this.msgData = msgData;
	}
	
	
	
}
