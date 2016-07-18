package io.venkat.bookstore.db;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;

public class DBConfiguration {
	
	private DBConfiguration(){
		
	}
	
	private static DatabaseClient mlClient = DatabaseClientFactory.newClient(
			ApplicationProperties.getProperty("db-hostname"), new Integer(ApplicationProperties.getProperty("db-port")), ApplicationProperties.getProperty("db-name"), ApplicationProperties.getProperty("db-username"), ApplicationProperties.getProperty("db-password"), DatabaseClientFactory.Authentication.DIGEST);
		
	public static DatabaseClient getMarkLogicClient() {
		return mlClient;
	}

}
