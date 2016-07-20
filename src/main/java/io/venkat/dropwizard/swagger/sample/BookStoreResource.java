package io.venkat.dropwizard.swagger.sample;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import io.venkat.bookstore.dao.BookStoreRepository;
import io.venkat.bookstore.domain.Author;
import io.venkat.bookstore.domain.Book;
import io.venkat.bookstore.domain.BookStoreResponse;

/**
 * 
 * @author Venkat
 *
 */
@Path("/bookstore")
@Api("/bookstore")
@Produces(MediaType.APPLICATION_JSON)
public class BookStoreResource {
	
	private BookStoreRepository bookStoreRepository;
	
	public BookStoreResource(BookStoreRepository bookStoreRepository) {
		this.bookStoreRepository = bookStoreRepository;
	}

	@GET
	@ApiOperation("BookStore endpoint")
	public Response getBook() {
		return Response.ok().entity("SUCCESS").build();
	}

	@GET
	@ApiOperation("BookStore endpoint for books /books ")
	@Path("/books")
	public BookStoreResponse getBookList() {
		
		BookStoreResponse response = new BookStoreResponse();
		response.setBooks(bookStoreRepository.findByTitle(""));
		
		return response;
	}

	@GET
	@ApiOperation("BookStore endpoint for books /books/{searchKey}")
	@Path("/books/{searchKey}")
	public BookStoreResponse findBookBySearchKey(@PathParam("searchKey") String searchKey) {
		
		BookStoreResponse response = new BookStoreResponse();
		response.setBooks(bookStoreRepository.findByTitle(searchKey));

		return response;
	}
	
	@GET
	@ApiOperation("BookStore endpoint for books /{isbn}")
	@Path("/{isbn}")
	public String findBookByIsbn(@PathParam("isbn") String isbn) {

		return bookStoreRepository.getBook(isbn);
	}

	@DELETE
	@ApiOperation("BookStore endpoint for books /books/{isbn}")
	@Path("/books/{isbn}")
	public Response deleteBook(@PathParam("isbn") String isbn) {
		bookStoreRepository.removeBook(isbn);
		return Response.ok().entity("SUCCESS").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@ApiOperation(value = "BookStore endpoint for Add a Book", notes = "Adding a new book and get book details", response = Book.class)
	@Path("/books")
	public Response addBook(@FormParam("isbn") @ApiParam(defaultValue = "isbn") String isbn,
			@FormParam("price") @ApiParam(defaultValue = "price") double price,
			@FormParam("edition") @ApiParam(defaultValue = "edition") double edition,
			@FormParam("title") @ApiParam(defaultValue = "title") String title,
			@FormParam("author") @ApiParam(defaultValue = "firstName,LastName") String author) {
		
		Author authors = new Author();
		if (!author.isEmpty() && author != null && author.contains(",")){
			String[] names = author.split(",");
			authors.setFirstName(names[0]);
			authors.setLastName(names[1]);
		}
		Book book = new Book(isbn, price, edition, title, authors);
		bookStoreRepository.addBook(isbn, book);
		return Response.ok().entity("SUCCESS").build();
	}

}
