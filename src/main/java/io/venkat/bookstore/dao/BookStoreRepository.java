package io.venkat.bookstore.dao;

import java.util.List;

import io.venkat.bookstore.domain.Book;

/**
 * 
 * @author Venkat
 *
 */
public interface BookStoreRepository {
	
	void addBook(String id, Book book);
    
	String getBook(String isbn);

    void removeBook(String isbn);

    List<String> findByTitle(String title);

}
