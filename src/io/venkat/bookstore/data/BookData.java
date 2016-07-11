package io.venkat.bookstore.data;

import java.util.ArrayList;
import java.util.List;

import io.venkat.bookstore.domain.Author;
import io.venkat.bookstore.domain.Book;


/**
 * 
 * @author Venkat
 *
 */
public class BookData {

	static private List<Book> books = new ArrayList<Book>();

	static {

		books.add(createBook("1234", 500.0, 4.0, "Java", new Author("Venkat", "Rao")));
		books.add(createBook("1235", 600.0, 1.0, "C", new Author("Balaguruswamy", "G")));
		books.add(createBook("1236", 1500.0, 2.0, "MassiveData", new Author("Jefry", "Ullman")));
		books.add(createBook("1237", 1600.0, 3.0, "DataMining", new Author("Han", "G")));
		books.add(createBook("1238", 800.0, 5.0, "MachineLearning", new Author("Ian", "A")));
	}

	public Book findBookByTitle(String title) {
		for (Book book : books) {
			if (book.getTitle().equals(title)) {
				return book;
			}
		}
		return null;
	}

	public void addBook(Book book) {
		if (book.getTitle() == null)
			return;
		if (books.size() > 0) {
			for (int i = books.size() - 1; i >= 0; i--) {
				if (books.get(i).getTitle().equals(book.getTitle())) {
					books.remove(i);
				}
			}
		}
		books.add(book);
	}

	public boolean removeBook(String title) {
		if (books.size() > 0) {
			for (int i = books.size() - 1; i >= 0; i--) {
				if (books.get(i).getTitle().equals(title)) {
					books.remove(i);
					return true;
				}
			}
		}
		return false;
	}

	private static Book createBook(String isbn, double price, double edition, String title, Author author) {
		Book book = new Book();
		book.setIsbn(isbn);
		book.setEdition(edition);
		book.setPrice(price);
		book.setTitle(title);
		book.setAuthor(author);
		return book;
	}
	
	public static List<Book> getBooks(){
		return books;
	}

}