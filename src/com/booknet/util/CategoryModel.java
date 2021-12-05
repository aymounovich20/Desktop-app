package com.booknet.util;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CategoryModel {
 private int id;
 private String name;
 @FXML
 private Button update = new Button("update");
 
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Button getUpdate() {
	return update;
}

public void setUpdate(Button update) {
	this.update = update;
}

public CategoryModel(int id, String name, Button update) {
	super();
	this.id = id;
	this.name = name;
	this.update = update;
}
 
}
