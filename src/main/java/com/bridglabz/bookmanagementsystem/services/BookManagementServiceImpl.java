package com.bridglabz.bookmanagementsystem.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.bridglabz.bookmanagementsystem.dao.BookDao;
import com.bridglabz.bookmanagementsystem.interfaces.IBookDao;
import com.bridglabz.bookmanagementsystem.interfaces.IBookManagementService;
import com.bridglabz.bookmanagementsystem.interfaces.ICustomerService;
import com.bridglabz.bookmanagementsystem.model.Book;
import com.bridglabz.bookmanagementsystem.model.BookManagement;
import com.bridglabz.bookmanagementsystem.model.Customer;

public class BookManagementServiceImpl implements IBookManagementService {
	static Scanner scan = new Scanner(System.in);
	IBookDao bookDao = new BookDao();
	static Book book = new Book();
	static BookManagement bookManagement = new BookManagement();

	ICustomerService customerService = new CustomerServiceImpl();

	@Override
	public void buyBook(List<Book> books, List<Customer> customers, List<BookManagement> bookManagements) {

//		Book book = books.stream()
//				.filter(data -> data.getBookName().compareToIgnoreCase(getBookNameFromCustomer().get(0)) == 0)
//				.collect(Collectors.toList()).get(0);

		System.out.print("Enter bookName - ");
		String bookName = scan.next();

		System.out.print("Enter bookEdition - ");
		String bookEdition = scan.next();

		System.out.print("Enter quantity - ");
		int quantity = scan.nextInt();

		if (BookManagementServiceImpl.this.findBook(bookName, bookEdition)) {
			Customer customer = customerService.getCustomer(customers);
			saveBookTransitionDetails(customer, book, quantity, bookManagements);

		} else {
			System.out.println("book is not present");
		}

	}

	private List<String> getBookNameFromCustomer() {
		System.out.println("Enter book name");
		String bookName = scan.next();
		System.out.println("Enter number of quantity -");
		int quantity = scan.nextInt();
		List<String> bookDetail = new ArrayList<String>();
		bookDetail.add(bookName);
		bookDetail.add(String.valueOf(quantity));
		return bookDetail;

	}

	public void saveBookTransitionDetails(Customer customer, Book book, int quantity,
			List<BookManagement> bookManagements) {

		bookManagement.setBookId(book.getId());
		bookManagement.setCustomerId(customer.getId());
		bookManagement.setManagementId((int) Math.random() + 2);

		Date date = new Date();
		bookManagement.setDateOfIssue(date);
		Date newDate = new Date(date.getYear(), date.getMonth(), date.getDay() + 10);
		bookManagement.setDataOfReturn(newDate);

		bookManagement.setQuantity((Integer.valueOf(quantity)));
		bookManagement.setTotalBillAmount((Integer.valueOf(quantity)) * book.getPrice());
		bookManagements.add(bookManagement);
		//bookManagement.setQuantity(bookManagement.getQuantity() - (Integer.valueOf(quantity)));
		bookDao.makeEntry(bookManagement);

	}

	@Override
	public boolean findBook(String nameOfBook, String bookEdition) {
		int flag = 0;
		for (int i = 0; i < bookDao.getBooks().size(); i++) {
			String name = bookDao.getBooks().get(i).getBookName();
			String edition = bookDao.getBooks().get(i).getEdition();
			if (name.equals(nameOfBook) && edition.equals(bookEdition)) {
				flag = 1;
				book.setAuthor(bookDao.getBooks().get(i).getAuthor());
				book.setBookName(bookDao.getBooks().get(i).getBookName());
				book.setEdition(bookDao.getBooks().get(i).getEdition());
				book.setId(bookDao.getBooks().get(i).getId());
				book.setPrice(bookDao.getBooks().get(i).getPrice());
				book.setQuantity(bookDao.getBooks().get(i).getQuantity());
			}

		}

		if (flag == 1) {
			return true;
		} else {
			return false;
		}

	}

	
}
