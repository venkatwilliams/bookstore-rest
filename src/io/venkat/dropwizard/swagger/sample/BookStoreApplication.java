package io.venkat.dropwizard.swagger.sample;

import com.wordnik.swagger.jaxrs.config.BeanConfig;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * @author Venkat
 */
public class BookStoreApplication extends Application<BookStoreConfiguration> {

    public static void main(String... args) throws Exception {
        new BookStoreApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<BookStoreConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<BookStoreConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(BookStoreConfiguration sampleConfiguration) {
                // this would be the preferred way to set up swagger, you can also construct the object here programtically if you want
                return sampleConfiguration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(BookStoreConfiguration configuration, Environment environment) throws Exception {
        // add your resources as usual
        environment.jersey().register(new BookStoreResource());
        BeanConfig config = new BeanConfig();
        config.setTitle("Swagger BookStore  REST API");
        config.setVersion("1.0.0");
        config.setResourcePackage("io.venkat.dropwizard.swagger.sample");
        config.setScan(true);
    }
}
