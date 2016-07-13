package io.venkat.dropwizard.swagger.sample;

import java.util.Iterator;

import org.glassfish.jersey.internal.ServiceFinder;

import com.google.common.collect.Iterators;

/**
 * 
 * @author Venkat
 *
 */
public class Jersey2ServiceIteratorProvider extends ServiceFinder.ServiceIteratorProvider {

	ServiceFinder.DefaultServiceIteratorProvider delegate = new ServiceFinder.DefaultServiceIteratorProvider();

	@Override
	public <T> Iterator<T> createIterator(Class<T> service, String serviceName, ClassLoader loader,
			boolean ignoreOnClassNotFound) {
		return delegate.createIterator(service, serviceName, loader, ignoreOnClassNotFound);
	}

	@Override
	public <T> Iterator<Class<T>> createClassIterator(Class<T> service, String serviceName, ClassLoader loader,
			boolean ignoreOnClassNotFound) {
		final Iterator<Class<T>> delegateClassIterator = delegate.createClassIterator(service, serviceName, loader,
				ignoreOnClassNotFound);
		return Iterators.filter(delegateClassIterator, input -> !input.toString().startsWith("class com.sun.jersey"));
	}
}