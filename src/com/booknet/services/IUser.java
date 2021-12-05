package com.booknet.services;

import java.sql.ResultSet;
import java.util.List;

import com.booknet.entities.Book;
import com.booknet.entities.Category;
import com.booknet.entities.User;

public interface IUser {
	
	public int signUpUser(User user);
	public ResultSet logInUser(User user);
	public List<User> allUsers();
	public User findUserById(int id);
	public int saveUser(User user);
	public ResultSet getAllUser();

}
