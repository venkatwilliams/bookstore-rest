package io.venkat.dropwizard.swagger.sample;

import org.glassfish.jersey.internal.ServiceFinder;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.wordnik.swagger.jaxrs.config.BeanConfig;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.venkat.bookstore.dao.BookStoreRepository;
import io.venkat.bookstore.dao.MarkLogicBookStoreRepository;

/**
 * @author Venkat
 */
public class BookStoreApplication extends Application<BookStoreConfiguration> {

    public static void main(String... args) throws Exception {
        new BookStoreApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<BookStoreConfiguration> bootstrap) {
    	ServiceFinder.setIteratorProvider(new Jersey2ServiceIteratorProvider());
        bootstrap.addBundle(new SwaggerBundle<BookStoreConfiguration>() {
            @Override
            public SwaggerBundleConfiguration getSwaggerBundleConfiguration(BookStoreConfiguration sampleConfiguration) {
                // this would be the preferred way to set up swagger, you can also construct the object here programtically if you want
                return sampleConfiguration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(BookStoreConfiguration configuration, Environment environment) throws Exception {
        // add your resources as usual
    	DatabaseClient client = DatabaseClientFactory.newClient("localhost", 8000,"bookstore", "admin", "admin",
				Authentication.DIGEST);    	
    	BookStoreRepository bookStoreRepository = new MarkLogicBookStoreRepository(client.newXMLDocumentManager(), client.newQueryManager());
    	
        environment.jersey().register(new BookStoreResource(bookStoreRepository));
        BeanConfig config = new BeanConfig();
        config.setTitle("Swagger BookStore  REST API");
        config.setVersion("1.0.0");
        config.setResourcePackage("io.venkat.dropwizard.swagger.sample");
        config.setScan(true);
    }
}
