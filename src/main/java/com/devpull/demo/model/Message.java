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
	
	@Column(name="text")
	private String msgData;
	
	public Message() {
	}
	
	

	public Message(User receiverMsg, User senderMsg, String msgData) {
		this.receiverMsg = receiverMsg;
		this.senderMsg = senderMsg;
		this.msgData = msgData;
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


	public String getMsgData() {
		return msgData;
	}

	public void setMsgData(String msgData) {
		this.msgData = msgData;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", receiverMsg=" + receiverMsg + ", senderMsg=" + senderMsg + ", msgData="
				+ msgData + "]";
	}
	
	
	
}
