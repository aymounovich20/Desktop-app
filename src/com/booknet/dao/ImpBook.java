package com.booknet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.booknet.entities.Book;
import com.booknet.services.IBook;
import com.booknet.util.DbConnection;

public class ImpBook implements IBook {

	@Override
	public Book findBookById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet allBooks() {
		Connection conn = DbConnection.openConnection();
		ResultSet rs = null;
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM book");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
