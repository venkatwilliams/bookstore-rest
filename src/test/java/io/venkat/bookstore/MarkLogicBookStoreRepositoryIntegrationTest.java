package io.venkat.bookstore;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.io.JacksonHandle;

import io.venkat.bookstore.dao.BookStoreRepository;
import io.venkat.bookstore.dao.MarkLogicBookStoreRepository;
import io.venkat.bookstore.domain.Author;
import io.venkat.bookstore.domain.Book;

public class MarkLogicBookStoreRepositoryIntegrationTest {
	
	private static final String NAME = "DataMining";
    private static final String SAMPLE_BOOK = "DataMining";
    private static Book book = new Book("001",1500.0,1.0,"Computer Networks", new Author("Venkat", "Rao"));
    
	private BookStoreRepository bookStoreRepository;
	
	@Before
    public void setUp() {
        DatabaseClient client = DatabaseClientFactory.newClient("localhost", 8000,"bookstore", "admin", "admin", Authentication.DIGEST);
        bookStoreRepository = new MarkLogicBookStoreRepository(client.newXMLDocumentManager(), client.newQueryManager());
        
    }
	
	@Test
    public void shouldAddAndRetrieveBookAsXmlDocument() {
        // given
        String isbn = UUID.randomUUID().toString();        
        book.setIsbn(isbn);
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.convertValue(book, JsonNode.class);
        JacksonHandle handle = new JacksonHandle(node);
        JsonNode node2 = handle.get();
        System.out.println("MarkLogicBookStoreRepositoryIntegrationTest before saving"+ node2.toString());
        
        // then
        bookStoreRepository.addBook(isbn, book);
        // when
        String result = bookStoreRepository.getBook(isbn);
        System.out.println("MarkLogicBookStoreRepositoryIntegrationTest After saving"+ result);
        
        assertThat(node2.toString(), is(result));
    }

}
