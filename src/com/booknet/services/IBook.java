package com.booknet.services;

import java.sql.ResultSet;

import com.booknet.entities.Book;

public interface IBook {
	public Book findBookById(int id);
	public ResultSet allBooks();

}
