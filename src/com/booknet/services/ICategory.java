package com.booknet.services;

import java.sql.ResultSet;

import com.booknet.entities.Category;

public interface ICategory {
	
	public int addCategory(Category category);
	public ResultSet findByName(String name);
	public ResultSet getAllCategories();
	public int deleteCategoryByName(String name);
	public int updateCategory(int id,String newName);
	public int findCategoryIdByName(String name);
	

}
