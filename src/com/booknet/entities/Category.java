package com.booknet.entities;

public class Category {

	private int id;
	private String name;	
	
	
	
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
