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
	
	@OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
	@JoinColumn(name="receiver_id")
	private User receiverMsg;
	
	@OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
	@JoinColumn(name="sender_id")
	private User senderMsg;
	
	
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

	public User getReceiver() {
		return receiverMsg;
	}

	public void setReceiver(User receiver) {
		this.receiverMsg = receiver;
	}

	public User getSender() {
		return senderMsg;
	}

	public void setSender(User sender) {
		this.senderMsg = sender;
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
