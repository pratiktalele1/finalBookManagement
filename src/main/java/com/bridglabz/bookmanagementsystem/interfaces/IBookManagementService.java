package com.bridglabz.bookmanagementsystem.interfaces;

import java.util.List;

import com.bridglabz.bookmanagementsystem.model.Book;
import com.bridglabz.bookmanagementsystem.model.BookManagement;
import com.bridglabz.bookmanagementsystem.model.Customer;

public interface IBookManagementService {
	void buyBook(List<Book> books, List<Customer> customers, List<BookManagement> bookManagements);
	boolean findBook(String nameOfBook, String bookEdition);
}
