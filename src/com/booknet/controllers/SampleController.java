package com.booknet.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.booknet.util.SceneUtils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SampleController implements Initializable {
	@FXML
	private Button btn_login ;
	@FXML
	private TextField tf_email;
	@FXML
	private TextField tf_password;
	@FXML
	private Button btn_signup;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn_login.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				SceneUtils.loginUser(event, tf_email.getText(), tf_password.getText());
				
			}
		});
		btn_signup.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					SceneUtils.changeScene(event, "sign-up.fxml", "signUp", null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		
	}

}
