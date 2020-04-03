package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

public class Admininformation implements Serializable {

	private String adminEmailId = "jessy@gmail.com";
	private String adminPassword = "Jessy@123";

	public String getEmail() {
		return adminEmailId;
	}

	public void setEmail(String email) {
		this.adminEmailId = email;
	}

	public String getPassword() {
		return adminPassword;
	}

	public void setPassword(String password) {
		this.adminPassword = password;
	}

}
