package com.bridglabz.bookmanagementsystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bridglabz.bookmanagementsystem.connection.DBConnection;
import com.bridglabz.bookmanagementsystem.interfaces.IBookDao;
import com.bridglabz.bookmanagementsystem.model.Book;
import com.bridglabz.bookmanagementsystem.model.BookManagement;

public class BookDao implements IBookDao {

	Connection connection = DBConnection.getConnection();
	Statement statement;

	@Override
	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		try {
			statement = connection.createStatement();
			String query = "select * from booksdetails;";

			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Book book = new Book();
				book.setId(Integer.valueOf(resultSet.getString(1)));
				book.setAuthor(resultSet.getString(2));
				book.setBookName(resultSet.getString(3));
				book.setPrice(Double.valueOf(resultSet.getString(5)));
				book.setQuantity(Integer.valueOf(resultSet.getString(6)));
				book.setEdition(resultSet.getString(4));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;

	}

	@Override
	public void insertBook(Book book) {
		try {
			statement = connection.createStatement();

			String insertQuery = "insert into booksdetails(id,author,bookName,edition,price,quantity) values("
					+ book.getId() + "," + "'" + book.getAuthor() + "'" + "," + "'" + book.getBookName() + "'" + ","
					+ "'" + book.getEdition() + "'" + "," + "'" + book.getPrice() + "'" + "," + "'" + book.getQuantity()
					+ "'" + ");";
			System.out.println(insertQuery);

			statement.execute(insertQuery);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void makeEntry(BookManagement bookManagement) {
		try {
			statement = connection.createStatement();

			String insertQuery = "insert into management(customerId,bookId,dateOfIssue,dataOfReturn,quantity,totalBillAmount) values("
					+ bookManagement.getCustomerId() + "," + "'" + bookManagement.getBookId() + "'" + "," + "'"
					+ bookManagement.getDateOfIssue() + "'" + "," + "'" + bookManagement.getDataOfReturn() + "'" + ","
					+ "'" + bookManagement.getQuantity() + "'" + "," + "'" + bookManagement.getTotalBillAmount() + "'"
					+ ");";
			System.out.println(insertQuery);

			statement.execute(insertQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
