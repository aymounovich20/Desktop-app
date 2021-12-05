package com.booknet.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SceneUtils {
	
	public static void changeScene(ActionEvent event,String fxmlFile,String title,String email) throws IOException {
		 Parent root = FXMLLoader.load(SceneUtils.class.getResource(fxmlFile));
		  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  Scene scene = new Scene(root);
		  stage.setScene(scene);
		  stage.setTitle(title);
		  stage.show();
	}
	public static void signUpUser(ActionEvent event,String email,String password) {
		
		Connection conn = DbConnection.openConnection();
		PreparedStatement insert = null;
		PreparedStatement checkUser = null;
		ResultSet rs = null;
		try {
			//Statement statement = conn.createStatement();
			Statement stmt = conn.createStatement();
			System.out.println(email);
			rs = stmt.executeQuery("SELECT email FROM user WHERE email='"+email+"'");
			//rs = checkUser.executeQuery();
			if(rs.isBeforeFirst()) {
				System.out.println("user already exist");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Email already used");
				alert.show();
			}else {
//				insert = conn.prepareStatement("INSERT INTO user(email,password) VALUES(?,?)");
//				insert.setString(1, email);
//				insert.setString(2, password);
//				insert.executeQuery();
				
				int result = stmt.executeUpdate("INSERT INTO user(email,password) values('"+email+"','"+password+"')");
				//changeScene(event, "logged-in.fxml", "welcome", email);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	public static void loginUser(ActionEvent event,String email,String password) {
		Connection conn = DbConnection.openConnection();
		PreparedStatement pp = null;
		ResultSet rs = null;
		try {
			pp = conn.prepareStatement("SELECT password FROM user WHERE email = ?");
			pp.setString(1, email);
			rs = pp.executeQuery();
			if(!rs.isBeforeFirst()) {
				System.out.println("user does not exist");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("email does not exist");
				alert.show();
			}else {
				while(rs.next()) {
					String get = rs.getString("password");
					if(get.equals(password)) {
						//changeScene(event, "logged-in.fxml", "welcome", email);
					}else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("wrong password");
						alert.show();
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
