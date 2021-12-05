package com.booknet.controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class indexController implements Initializable {
	
	
	public void switchToLogin(ActionEvent event) {
		
		
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/booknet/fxml/Sample.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Login USER");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void switchToSignUp(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("/com/booknet/fxml/sign-up.fxml"));
		  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  Scene scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("signUp");
		  stage.show();
		 }
	public void switchToContact(ActionEvent event) throws IOException{
		  Parent root = FXMLLoader.load(getClass().getResource("/com/booknet/fxml/contact.fxml"));
		  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  Scene scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Contact");
		  stage.show();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

}
