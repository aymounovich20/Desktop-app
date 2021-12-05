package com.booknet.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.booknet.util.DbConnection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogAController implements Initializable {
	@FXML
	private TextField tf_email;
	@FXML
	private TextField tf_password;

	public void switchToUser(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/com/booknet/fxml/Sample.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("User Login");
		stage.show();
	}

	public void login(ActionEvent event) {
		Connection conn = DbConnection.openConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT password FROM user WHERE email='" + tf_email.getText() + "' AND role='ADMIN'");
			if (!rs.isBeforeFirst()) {
				System.out.println("admin does not exist");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("email does not exist");
				alert.show();
			} else {
				while (rs.next()) {
					String get = rs.getString("password");
					if (get.equals(tf_password.getText())) {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/booknet/fxml/admin-panel.fxml"));
						Parent root = loader.load();
						AdminPanelController panelController = loader.getController();
						panelController.userInformation(tf_email.getText());
						panelController.showBooks();
						Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						Scene scene = new Scene(root);
						stage.setScene(scene);
						stage.setTitle("logged-in");
						stage.show();
					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("wrong password");
						alert.show();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
