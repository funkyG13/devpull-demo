package com.devpull.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="message")
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	 @JsonIgnore
	@OneToOne(cascade=CascadeType.ALL,orphanRemoval= true)
	@JoinColumn(name="receiver_id")
	private User receiverMsg;
	
	 @JsonIgnore
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval= true)
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

	public User getReceiverMsg() {
		return receiverMsg;
	}

	public void setReceiverMsg(User receiver) {
		this.receiverMsg = receiver;
	}

	public User getSenderMsg() {
		return senderMsg;
	}

	public void setSenderMsg(User sender) {
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
