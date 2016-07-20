package io.venkat.bookstore.dao;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.io.StringHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StringQueryDefinition;

import io.venkat.bookstore.domain.Book;

/**
 * 
 * @author Venkat
 *
 */
public class MarkLogicBookStoreRepository implements BookStoreRepository{
	
	private XMLDocumentManager documentManager;
    private QueryManager queryManager;
    private static final int PAGE_SIZE = 5;

    public MarkLogicBookStoreRepository(XMLDocumentManager documentManager, QueryManager queryManager) {
        this.documentManager = documentManager;
        this.queryManager = queryManager;
    }

	@Override
	public String getBook(String isbn) {
		JacksonHandle handle = new JacksonHandle();
        documentManager.read(isbn, handle);
        JsonNode node = handle.get();
        return node.toString();
	}

	@Override
	public void removeBook(String isbn) {
		documentManager.delete(isbn);
		
	}

	@Override
	public List<String> findByTitle(String title) {
		StringQueryDefinition query = queryManager.newStringDefinition();
        queryManager.setPageLength(10);  // LIMIT RESULT
        int pageNum = 1;
        int start = PAGE_SIZE * (pageNum - 1) + 1;
        query.setCriteria(title);
        //query.put(queryManager.newElementLocator(new QName("name")), name);
        SearchHandle resultsHandle = new SearchHandle();
        queryManager.search(query, resultsHandle, start);
        return getResultListFor(resultsHandle);
	}
	
	@Override
	public void addBook(String id, Book book) {
		ObjectMapper mapper = new ObjectMapper(); 
		JsonNode node = mapper.convertValue(book, JsonNode.class);
		JacksonHandle handle = new JacksonHandle(node);
        documentManager.write(id, handle);		
	}
	
	private List<String> getResultListFor(SearchHandle resultsHandle) {
        List<String> result = new ArrayList<String>();
        for (MatchDocumentSummary summary : resultsHandle.getMatchResults()) {
        	//JacksonHandle content = new JacksonHandle();
        	//StringHandle content = new StringHandle();
        	JacksonHandle content = new JacksonHandle();
            documentManager.read(summary.getUri(), content);
            JsonNode node = content.get();
            //JsonNode doc = content.get();
            result.add(node.toString());
        }
        return result;
    }	

}
