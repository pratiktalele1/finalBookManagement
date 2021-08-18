package com.bridglabz.bookmanagementsystem.services;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bridglabz.bookmanagementsystem.connection.DBConnection;
import com.bridglabz.bookmanagementsystem.dao.BookDao;
import com.bridglabz.bookmanagementsystem.interfaces.IBookDao;
import com.bridglabz.bookmanagementsystem.interfaces.IBookService;
import com.bridglabz.bookmanagementsystem.model.Book;
import com.google.gson.Gson;

//import Service.UserInputError;

class UserInputError extends Exception {
	UserInputError(String exception) {
		super(exception);
	}
}

public class BookServiceImpl implements IBookService {
	Scanner scanInput = new Scanner(System.in);
	File file = new File("data1.txt");
	IBookDao bookDao = new BookDao();

	@Override
	public void addBook(List<Book> books) {
		Book book = new Book();
		try {

			System.out.print("Enter book name- ");
			String bookName = scanInput.next();

			System.out.println("enter book edition -");
			String bookEdition = scanInput.next();

			if (findBook(bookName, bookEdition)) {

				System.out.print("book allready present - ");

			} else {
				System.out.println("enter book id");
				int getId = scanInput.nextInt();

				String bookId = (Integer.toString(getId));

				book.setId(getId);
				book.setBookName(bookName);

				System.out.println("enter book author -");
				String bookAuthor = scanInput.next();

				book.setAuthor(bookAuthor);

				book.setEdition(bookEdition);

				System.out.println("enter book price -");
				Double bookPrice = scanInput.nextDouble();

				book.setPrice(bookPrice);

				System.out.println("enter book quantity -");
				int bookQuantity = scanInput.nextInt();

				book.setQuantity(bookQuantity);

				bookDao.insertBook(book);

			}

		} catch (Exception e) {
			System.out.println("found :: " + e);
		}

	}

	public void updateBookDetails(List<Book> books, DBConnection connect) {
		Connection connection = connect.getConnection();
		try {

			System.out.print("Enter book name- ");
			String bookName = scanInput.next();

			System.out.println("enter book edition -");
			String bookEdition = scanInput.next();

			if (findBook(bookName, bookEdition)) {

				for (Book book : bookDao.getBooks()) {

					if (book.getBookName().equals(bookName)) {
						System.out.println("1-> update book ID\t " + "2-> update author\t " + "3-> update bookName\t "
								+ "4-> update edition\t " + "5-> update price\t " + "6-> update quantity");

						int getUserInput = scanInput.nextInt();
						switch (getUserInput) {

						case 1: {

							System.out.print("Enter new book ID - ");
							int changeBookId = scanInput.nextInt();
							Statement statement = connection.createStatement();
							String updateQuery = "update booksdetails set id = " + "'" + changeBookId + "'"
									+ " where id = " + "'" + book.getId() + "'";
							statement.executeUpdate(updateQuery);

							break;
						}
						case 2: {
							System.out.print("Enter new book author - ");
							String changeBookAuthor = scanInput.next();
							Statement statement = connection.createStatement();
							String updateQuery = "update booksdetails set author = " + "'" + changeBookAuthor + "'"
									+ " where id = " + "'" + book.getId() + "'";
							statement.executeUpdate(updateQuery);

							break;
						}
						case 3: {
							System.out.print("Enter new bookName - ");
							String changeBookName = scanInput.next();
							Statement statement = connection.createStatement();
							String updateQuery = "update booksdetails set bookName = " + "'" + changeBookName + "'"
									+ " where id = " + "'" + book.getId() + "'";
							statement.executeUpdate(updateQuery);
							break;
						}
						case 4: {
							System.out.print("Enter new book edition - ");
							String changeBookEdition = scanInput.next();
							Statement statement = connection.createStatement();
							String updateQuery = "update booksdetails set edition = " + "'" + changeBookEdition + "'"
									+ " where id = " + "'" + book.getId() + "'";
							statement.executeUpdate(updateQuery);

							break;
						}
						case 5: {
							System.out.print("Enter new book price - ");
							Double changeBookPrice = scanInput.nextDouble();

							Statement statement = connection.createStatement();
							String updateQuery = "update booksdetails set price = " + "'" + changeBookPrice + "'"
									+ " where id = " + "'" + book.getId() + "'";
							statement.executeUpdate(updateQuery);

							break;
						}
						case 6: {
							System.out.print("Enter new book quantity - ");
							int changeBookQuantity = scanInput.nextInt();
							Statement statement = connection.createStatement();
							String updateQuery = "update booksdetails set quantity = " + "'" + changeBookQuantity + "'"
									+ " where id = " + "'" + book.getId() + "'";
							statement.executeUpdate(updateQuery);
							break;
						}

						}

					}

				}

			} else {
				System.out.println("book does not exist try for another book ");
			}

		} catch (Exception e) {
			System.out.println("Found input Exception :: " + e);
		}

	}

	@Override
	public boolean findBook(String nameOfBook, String bookEdition) {
		int flag = 0;
		for (int i = 0; i < bookDao.getBooks().size(); i++) {
			String name = bookDao.getBooks().get(i).getBookName();
			String edition = bookDao.getBooks().get(i).getEdition();
			if (name.equals(nameOfBook) && edition.equals(bookEdition)) {
				flag = 1;
				
			}

		}

		if (flag == 1) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void deleteBook(String nameOfBook, String bookEdition,DBConnection connect) {
		Connection connection = connect.getConnection();
		Statement statement = null;
		

		for (int i = 0; i < bookDao.getBooks().size(); i++) {

			String name = bookDao.getBooks().get(i).getBookName();
			String edition = bookDao.getBooks().get(i).getEdition();

			if (name.equals(nameOfBook) && edition.equals(bookEdition)) {
				
				try {
					statement = connection.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String updateQuery="delete from booksdetails where bookName="+"'"+nameOfBook+"'"+" and "+"edition="+"'"+bookEdition+"'"+";";
				try {
					statement.executeUpdate(updateQuery);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		}

	}

}
