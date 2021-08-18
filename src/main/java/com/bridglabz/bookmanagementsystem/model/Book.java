package com.bridglabz.bookmanagementsystem.model;

public class Book {

	private int id;
	private String author;
	private String bookName;
	private String edition;
	private Double price;
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String string) {
		this.edition = string;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double double1) {
		this.price = double1;
	}

}
