package io.venkat.bookstore.domain;

import java.util.List;

public class BookStoreResponse {
	
	//private String book;
	private List<String> books;

	public List<String> getBooks() {
		return books;
	}

	public void setBooks(List<String> books) {
		this.books = books;
	}

}
