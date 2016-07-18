package io.venkat.bookstore.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author Venkat
 *
 */
public class ApplicationProperties {

	private static Properties properties = new Properties();

	static {
		InputStream input = null;
		try {
			input = new FileInputStream("src/main/resources/dbconfig.properties");
			properties.load(input);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(input!=null){
        		try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	}
		}

	}

	public static Properties getProperties() {

		return properties;

	}

	public static String getProperty(String key) {

		return properties.getProperty(key);

	}

}
