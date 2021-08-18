package com.bridglabz.bookmanagementsystem.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bridglabz.bookmanagementsystem.connection.DBConnection;
import com.bridglabz.bookmanagementsystem.dao.BookDao;
import com.bridglabz.bookmanagementsystem.interfaces.IBookDao;
import com.bridglabz.bookmanagementsystem.interfaces.IBookManagementService;
import com.bridglabz.bookmanagementsystem.interfaces.IBookService;
import com.bridglabz.bookmanagementsystem.model.Book;
import com.bridglabz.bookmanagementsystem.model.BookManagement;
import com.bridglabz.bookmanagementsystem.model.Customer;
import com.bridglabz.bookmanagementsystem.services.BookManagementServiceImpl;
import com.bridglabz.bookmanagementsystem.services.BookServiceImpl;


public class DashBoard {

	List<Customer> customers = new ArrayList<Customer>();
	List<Book> books = new ArrayList<Book>();
	List<BookManagement> bookManagements = new ArrayList<BookManagement>();
	
	IBookService bookService= new BookServiceImpl();
	IBookDao bookDao=new BookDao();
	DBConnection connection;
	IBookManagementService bookManagementService=new BookManagementServiceImpl();
	
	static Scanner scanInput = new Scanner(System.in);
	
	public void menu() {
		int i = 0;
		
//		List<Book> book= bookDao.getBooks();
//		for (Book book2 : book) {
//			System.out.println(book2.getId()+"->"+book2.getBookName()+"->"+book2.getAuthor()+"->"+book2.getEdition()+"->"+book2.getPrice()+"->"+book2.getQuantity());
//		}
		do {
			System.out.print("1->add book \t 2-> buy book \t 3->update book details ");
			
			int getUserInput = scanInput.nextInt();

			switch (getUserInput) {
			case 1: {
					bookService.addBook(books);
				break;
			}
			case 2: {
					bookManagementService.buyBook(books, customers, bookManagements);
				break;
			}
			case 3: {
				bookService.updateBookDetails(books,connection);
				break;
			}
			case 4: {
				
				System.out.print("Enter book name - ");
				String bookname = scanInput.next();
				
				System.out.print("Enter book edition - ");
				String bookEdition = scanInput.next();
				
				bookService.deleteBook(bookname, bookEdition,connection);
				break;
			}
			}

			System.out.print("want to continue- ");

			i = scanInput.nextInt();

		} while (i == 1);
	}
	

	public static void main(String[] args) {
		DashBoard board=new DashBoard();
		board.menu();
	}
}
