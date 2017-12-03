package com.gc.dto;

import java.util.ArrayList;
import java.util.List;

import com.gc.dao.Dao;

public class UserDto extends DBRecordDto {
	private String fullName;
	private String emailAddress;
	private String entered_password;
	private String pw_hash;
	private boolean authenticated;
	private boolean optIn;
	private ArrayList<String[][]> purchaseHistory;
	
	public UserDto() {
		
	}
	
	public UserDto(String fullName, String emailAddress, String entered_password, boolean optIn) {
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.entered_password = entered_password;
		this.optIn = optIn;
		
	}
	
	public String getEntered_password() {
		return entered_password;
	}

	public void setEntered_password(String entered_password) {
		this.entered_password = entered_password;
	}

	public UserDto(String fullName, String emailAddress, boolean optIn) {
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.optIn = optIn;
		
	}

	public UserDto(String emailAddress) {
		this.emailAddress = emailAddress;
		authenticated = false;
		// TODO Auto-generated constructor stub
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public boolean isOptIn() {
		return optIn;
	}

	public void setOptIn(boolean optIn) {
		this.optIn = optIn;
	}

	public ArrayList<String[][]> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(ArrayList<String[][]> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}
	public String getPw_hash() {
		return pw_hash;
	}

	public void setPw_hash(String pw_hash) {
		this.pw_hash = pw_hash;
	}
	
}