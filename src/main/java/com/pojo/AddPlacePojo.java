package com.pojo;

import java.util.List;

public class AddPlacePojo {
	
	private int accuracy;
	private String name;
	private String phoneNumber;
	private String address;
	private String webSite;
	private String launguage;
	private LocationPojo location;
	private List<String> type;
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getLaunguage() {
		return launguage;
	}
	public void setLaunguage(String launguage) {
		this.launguage = launguage;
	}
	public LocationPojo getLocation() {
		return location;
	}
	public void setLocation(LocationPojo location) {
		this.location = location;
	}
	public List<String> getType() {
		return type;
	}
	public void setType(List<String> type) {
		this.type = type;
	}
	
	
	
	
	
	

}
