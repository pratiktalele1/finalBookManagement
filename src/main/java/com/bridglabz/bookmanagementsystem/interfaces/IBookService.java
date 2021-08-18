package com.bridglabz.bookmanagementsystem.interfaces;

import java.util.List;

import com.bridglabz.bookmanagementsystem.connection.DBConnection;
import com.bridglabz.bookmanagementsystem.model.Book;

public interface IBookService {
	void addBook(List<Book> books);
	void updateBookDetails(List<Book> books,DBConnection connection);
	boolean findBook(String nameOfBook, String bookEdition);
	void deleteBook(String nameOfBook, String bookEdition,DBConnection connection);
}
