package com.tust.lamp.entity;

import java.util.Date;

public class Bespeak extends IdEntity {

	private User user;
	private Device device;
	private Date date;
	private Integer stage;
	private Date bespeakDate;
	
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	
	public Integer getStage() {
		return stage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getBespeakDate() {
		return bespeakDate;
	}

	public void setBespeakDate(Date bespeakDate) {
		this.bespeakDate = bespeakDate;
	}

}
