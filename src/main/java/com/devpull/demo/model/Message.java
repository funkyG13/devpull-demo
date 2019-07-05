package com.devpull.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


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
	
//	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="receiver_id", referencedColumnName="id")
	private User receiverMsg;
	
//	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sender_id", referencedColumnName="id")
	private User senderMsg;
	
	@Column(name="text")
	private String msgData;
	
	@Column(name="time")
	private Date time;
	
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		time = new Date();
		this.time = time;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", receiverMsg=" + receiverMsg.getId() + ", senderMsg=" + senderMsg.getId() + ", msgData="
				+ msgData + "]";
	}
	
	
	
}
