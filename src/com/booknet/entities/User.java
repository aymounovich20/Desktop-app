package com.booknet.entities;

public class User {
	private int id ;
	private String email;
	private String password;
	private String role;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}



	
	
	public User() {
		
	}


	public User(int id, String email) {
		this.id = id;
		this.email = email;
	}


	public User(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

}
