package io.venkat.dropwizard.swagger.sample;

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

import io.venkat.bookstore.data.BookData;
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

	@GET
	@ApiOperation("BookStore endpoint")
	public Response getBook(){
		return Response.ok().entity("SUCCESS").build();
	}
	
	@GET
	@ApiOperation("BookStore endpoint for books /list ")
	@Path("/list")
	public List<Book> getBookList(){
		return BookData.getBooks();
	}
	
	@GET
	@ApiOperation("BookStore endpoint for books /findByTitle")
	@Path("/findByTitle")
	public Book findByTitle(@QueryParam("title") String title){
		return bookData.findBookByTitle(title);
	}	
		
	@DELETE
	@ApiOperation("BookStore endpoint for books /deleteByTitle")
	@Path("/deleteByTitle")
	public boolean deleteBook(@QueryParam("title") String title){
		return bookData.removeBook(title);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @ApiOperation(
            value = "Add a Book",
            notes = "Adding a new book and get book details",
            response = Book.class
    )
	@Path("/add")
	public Response addBook(@FormParam("isbn") @ApiParam(defaultValue = "isbn") String isbn,
            @FormParam("price") @ApiParam(defaultValue = "price") double price,
            @FormParam("edition") @ApiParam(defaultValue = "edition") double edition,
            @FormParam("title") @ApiParam(defaultValue = "title") String title,
            @FormParam("author") @ApiParam(defaultValue = "firstName,LastName") String author
            ){
		Book book = new Book(isbn, price,edition, title, new Author(author,author));
		bookData.addBook(book);
		return Response.ok().entity(book).build();
	}
	
}
