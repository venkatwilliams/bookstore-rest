package io.venkat.bookstore;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


import org.junit.Before;
import org.junit.Test;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;

import io.venkat.bookstore.dao.BookStoreRepository;
import io.venkat.bookstore.dao.MarkLogicBookStoreRepository;

public class MarkLogicBookStoreRepositoryIntegrationTest {
	
	private static final String NAME = "DataMining";
    private static final String SAMPLE_BOOK = "DataMining";

	
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
        bookStoreRepository.addBook(isbn, SAMPLE_BOOK);
        // when
        String result = bookStoreRepository.getBook(isbn);
        // then
        assertThat(result, is(SAMPLE_BOOK));
    }

}
