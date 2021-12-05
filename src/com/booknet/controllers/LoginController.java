package com.booknet.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.booknet.util.DbConnection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	 @FXML
	 private TextField tf_email;
	 @FXML
	 private TextField tf_password;
	 public void switchToSignUp(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("/com/booknet/fxml/sign-up.fxml"));
		  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  Scene scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("signUp");
		  stage.show();
		 }
	 @FXML
	 private void login(ActionEvent event) throws IOException{
		 //System.out.println(tf_email.getText()+tf_password.getText());
		 Connection conn = DbConnection.openConnection();
		 Statement stmt = null;
		 ResultSet rs = null;
		 try {
			 stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT password FROM user WHERE email='"+tf_email.getText()+"' AND role='USER'");
			if(!rs.isBeforeFirst()) {
				System.out.println("user does not exist");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("email does not exist");
				alert.show();
			}else {
				while(rs.next()) {
					String get = rs.getString("password");
					if(get.equals(tf_password.getText())) {
						
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/booknet/fxml/logged-in.fxml"));
					    Parent root = loader.load();
						LoggedInController2 loggedInController = loader.getController();
     					loggedInController.setUserInformation(tf_email.getText());
						//FXMLLoader loader = new FXMLLoader(getClass().getResource("logged-in.fxml"));
						//Parent root = loader.load();
						//LoggedInController2 loggedInController = loader.getController();
						//loggedInController.setUserInformation(tf_email.getText());
						
						  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
						  
						  Scene scene = new Scene(root);
						  stage.setScene(scene);
						  stage.setTitle("logged-in");
						  stage.show();
					}else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("wrong password");
						alert.show();
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	 }
	 public void switchToAdmin(ActionEvent event) throws IOException {
		 Parent root = FXMLLoader.load(getClass().getResource("/com/booknet/fxml/adminLogin.fxml"));
		  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  Scene scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle("Admin Login");
		  stage.show();
	 }
	 
	 public void switchToIndex(ActionEvent event) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/com/booknet/fxml/index.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Login");
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


}
