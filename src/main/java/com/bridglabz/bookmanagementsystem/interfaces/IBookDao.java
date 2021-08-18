package com.bridglabz.bookmanagementsystem.interfaces;

import java.util.List;

import com.bridglabz.bookmanagementsystem.model.Book;
import com.bridglabz.bookmanagementsystem.model.BookManagement;

public interface IBookDao {
	List<Book> getBooks();
	void insertBook(Book book);
	void makeEntry(BookManagement bookManagement);
	
}
