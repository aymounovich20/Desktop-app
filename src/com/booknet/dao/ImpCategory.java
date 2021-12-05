package com.booknet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.booknet.entities.Category;
import com.booknet.services.ICategory;
import com.booknet.util.DbConnection;

public class ImpCategory implements ICategory {

	@Override
	public int addCategory(Category category) {
		Connection conn = DbConnection.openConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO category(name) VALUES('"+category.getName()+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ResultSet findByName(String name) {
		Connection conn = DbConnection.openConnection();
		ResultSet rs = null;
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name FROM category WHERE name='"+name+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getAllCategories() {
		Connection conn = DbConnection.openConnection();
		ResultSet rs = null;
		
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM category");
			System.out.println(rs.isBeforeFirst());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	@Override
	public int deleteCategoryByName(String name) {
		Connection conn = DbConnection.openConnection();
		try {
			Statement stmt = conn.createStatement();
			int qq = stmt.executeUpdate("DELETE FROM category WHERE name='"+name+"'");
			System.out.println(name+" deleted successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateCategory(int id,String newName) {
		Connection conn = DbConnection.openConnection();
		try {
			Statement stmt = conn.createStatement();
			int qq = stmt.executeUpdate("UPDATE category SET name='"+newName+"' WHERE id="+id);
			System.out.println("update category successful : "+qq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int findCategoryIdByName(String name) {
		Connection conn = DbConnection.openConnection();
		ResultSet rs = null;
		int id = 0;
		try {
			Statement statement = conn.createStatement();
			rs = statement.executeQuery("SELECT id FROM category WHERE name='"+name+"'");
			while(rs.next())
				id = rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	

}
