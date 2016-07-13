package io.venkat.bookstore.dao;

import java.util.List;

/**
 * 
 * @author Venkat
 *
 */
public interface BookStoreRepository {
	
	void addBook(String id, String title);

    String getBook(String isbn);

    void removeBook(String isbn);

    List<String> findByTitle(String title);

}
