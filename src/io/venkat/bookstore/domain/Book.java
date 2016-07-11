package io.venkat.bookstore.domain;

/**
 * 
 * @author Venkat
 *
 */
public class Book {
	
	private String isbn;
	private double price;
	private double edition;
	private String title;
	private Author author;
	
	public Book(){
		
	}
	
	public Book(String isbn, double price, double edition, String title, Author author){
		this.isbn = isbn;
		this.price = price;
		this.edition = edition;
		this.title = title;
		this.author = author;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getEdition() {
		return edition;
	}
	public void setEdition(double edition) {
		this.edition = edition;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

}
