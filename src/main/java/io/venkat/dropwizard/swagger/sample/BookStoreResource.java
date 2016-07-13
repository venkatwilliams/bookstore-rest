package io.venkat.dropwizard.swagger.sample;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.fasterxml.jackson.databind.util.RawValue;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;

import io.venkat.bookstore.dao.BookData;
import io.venkat.bookstore.dao.BookStoreRepository;
import io.venkat.bookstore.dao.MarkLogicBookStoreRepository;
import io.venkat.bookstore.domain.Author;
import io.venkat.bookstore.domain.Book;

/**
 * 
 * @author Venkat
 *
 */
@Path("/bookstore")
@Api("/bookstore")
@Produces(MediaType.APPLICATION_JSON)
public class BookStoreResource {

	static BookData bookData = new BookData();
	
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
	@ApiOperation("BookStore endpoint for books /list ")
	@Path("/list")
	public List<String> getBookList() {
		
		return bookStoreRepository.findByTitle("");
	}

	@GET
	@ApiOperation("BookStore endpoint for books /findByTitle")
	@Path("/findByTitle")
	public List<String> findByTitle(@QueryParam("title") String title) {

		return bookStoreRepository.findByTitle(title);
	}

	@DELETE
	@ApiOperation("BookStore endpoint for books /deleteByTitle")
	@Path("/deleteByTitle")
	public Response deleteBook(@QueryParam("title") String isbn) {
		bookStoreRepository.removeBook(isbn);
		return Response.ok().entity("SUCCESS").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@ApiOperation(value = "Add a Book", notes = "Adding a new book and get book details", response = Book.class)
	@Path("/add")
	public Response addBook(@FormParam("isbn") @ApiParam(defaultValue = "isbn") String isbn,
			@FormParam("price") @ApiParam(defaultValue = "price") double price,
			@FormParam("edition") @ApiParam(defaultValue = "edition") double edition,
			@FormParam("title") @ApiParam(defaultValue = "title") String title,
			@FormParam("author") @ApiParam(defaultValue = "firstName,LastName") String author) {
		Book book = new Book(isbn, price, edition, title, new Author(author, author));
		bookStoreRepository.addBook(isbn, title);
		return Response.ok().entity("SUCCESS").build();
	}

}
