package com.booknet.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.booknet.util.DbConnection;
import com.booknet.util.JavaMailing;
import com.booknet.util.SceneUtils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
	@FXML
	Button btn_register;
	@FXML
	Button btn_login;
	@FXML
	TextField tf_email;
	@FXML
	TextField tf_password;
	Alert alert = new Alert(Alert.AlertType.ERROR);

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void switchToLogin(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/booknet/fxml/Sample.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Login");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void signUp(ActionEvent event) throws IOException {
		Connection conn = DbConnection.openConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT email FROM user WHERE email='" + tf_email.getText() + "'");
			if (rs.isBeforeFirst()) {
				System.out.println("user already exist");

				alert.setContentText("Email already used");
				alert.show();
			} else {
				if (tf_password.getText().length() == 0) {
					System.out.println("password empty");
					alert.setContentText("password empty");
					alert.show();
				} else {
					int result = stmt.executeUpdate("INSERT INTO user(email,password,role) values('"
							+ tf_email.getText() + "','" + tf_password.getText() + "','USER')");
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/booknet/fxml/logged-in.fxml"));
					Parent root = loader.load();
					LoggedInController2 loggedInController = loader.getController();
					loggedInController.setUserInformation(tf_email.getText());
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					JavaMailing mailing = new JavaMailing();
					mailing.sendMail(tf_email.getText(), "Welcome",
							tf_email.getText().substring(0, tf_email.getText().indexOf('@'))
									+ " Welcome to our Book Store 😍 enjoy !");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
