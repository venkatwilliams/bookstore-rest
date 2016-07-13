package io.venkat.bookstore.dao;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.io.StringHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StringQueryDefinition;

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
	public void addBook(String isbn, String title) {
		StringHandle handle = new StringHandle(title);
        documentManager.write(isbn, handle);
		
	}

	@Override
	public String getBook(String isbn) {
		StringHandle handle = new StringHandle();
        documentManager.read(isbn, handle);
        return handle.get();
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
	
	private List<String> getResultListFor(SearchHandle resultsHandle) {
        List<String> result = new ArrayList<String>();
        for (MatchDocumentSummary summary : resultsHandle.getMatchResults()) {
        	//JacksonHandle content = new JacksonHandle();
        	StringHandle content = new StringHandle();
            documentManager.read(summary.getUri(), content);
            //JsonNode doc = content.get();
            result.add(content.get());
        }
        return result;
    }

}
