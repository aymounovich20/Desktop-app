package com.booknet.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.booknet.util.SceneUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoggedInController2 implements Initializable {
	@FXML
	private Label label_welcome;
	@FXML
    private Button btn_addresses;
    @FXML
    private Button btn_logout;
    @FXML
    private Button btn_orders;
    @FXML
    private Button btn_profile;


//	public void switchedToLogged(ActionEvent event, String email) throws IOException {
//
//		root = FXMLLoader.load(getClass().getResource("logged-in.fxml"));
//		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		Scene scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
//
//		label_welcome.setText("Welcome " + email);
//	}
	public void setUserInformation(String email) {
		label_welcome.setText("Welcome " + email + " !");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void logOut(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/booknet/fxml/Sample.fxml"));
		Parent root = loader.load();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
