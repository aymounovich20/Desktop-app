package com.booknet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.booknet.entities.User;
import com.booknet.services.IUser;
import com.booknet.util.DbConnection;

public class ImpUser implements IUser {
	Connection conn = DbConnection.openConnection();
	Statement stmt = null;
	ResultSet rs = null;
	@Override
	public int signUpUser(User user) {
		
		try {
			stmt = conn.createStatement();
			int result = stmt.executeUpdate("INSERT INTO user(email,password,role) values('" + user.getEmail()
			+ "','" + user.getPassword() + "','USER')");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ResultSet logInUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> allUsers() {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<User> users = null ;
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				users.add(new User(id,email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User findUserById(int id) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user WHERE id="+id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs.next()) {
				return new User(id,rs.getString("email"),rs.getString("password"));}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultSet getAllUser() {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user WHERE role='USER'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
