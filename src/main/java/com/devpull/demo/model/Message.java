package com.devpull.demo.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="receiver_id", referencedColumnName="id")
	private User receiver;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sender_id", referencedColumnName="id")
	private User sender;
	
//	@Column(name="receiver_id")
//	private int receiverId;
//	
//	@Column(name="sender_id")
//	private int senderId;
	
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

//	public int getReceiverId() {
//		return receiverId;
//	}
//
//	public void setReceiverId(int receiverId) {
//		this.receiverId = receiverId;
//	}
//
//	public int getSenderId() {
//		return senderId;
//	}

//	public void setSenderId(int senderId) {
//		this.senderId = senderId;
//	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


	public String getMsgData() {
		return msgData;
	}

	public void setMsgData(String msgData) {
		this.msgData = msgData;
	}
	
	
	
}
