package com.booknet.entities;

import java.io.File;

public class Book {

	private int id;
	private String title;
	private String description;
	private float price;
	private int stock;
	private int idCategory;
	private File image;
	public Book(int id, String title, float price, int stock) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.stock = stock;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	


}
