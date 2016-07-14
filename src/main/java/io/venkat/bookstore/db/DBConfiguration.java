package io.venkat.bookstore.db;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;

public class DBConfiguration {
	
	private static DatabaseClient mlClient = DatabaseClientFactory.newClient(
		      "localhost", 8000, "bookstore", "admin", "admin", DatabaseClientFactory.Authentication.DIGEST);
		
	public static DatabaseClient getMarkLogicClient() {
		return mlClient;
	}

}
