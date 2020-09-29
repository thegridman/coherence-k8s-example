package com.oracle.coherence.examples.storage;

import java.util.logging.Logger;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.ConfigurableCacheFactory;
import com.tangosol.net.DefaultCacheServer;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * The main class of Coherence storage the application.
 *
 * @author Jonathan Knight  2020.09.10
 */
@SpringBootApplication
@EntityScan("com.oracle.coherence.examples.domain")  // JPA entities are in another package
public class StorageApplication {

	private static final Logger LOGGER = Logger.getLogger(StorageApplication.class.getName());

	/**
	 * The application entry pont.
	 *
	 * @param args  the application arguments.
	 */
    public static void main(String[] args) {
   		SpringApplication.run(StorageApplication.class, args);
   	}

	/**
	 * An {@link ApplicationRunner} bean that will cause the Coherence {@link DefaultCacheServer}
	 * to start.
	 * <p>
	 * This method will also register the Spring context into the {@link ConfigurableCacheFactory}
	 * {@link com.tangosol.util.ResourceRegistry} so that is is easily accessible by other
	 * Coherence code.
	 *
	 * @param ctx  the Spring {@link ApplicationContext}
	 *
	 * @return the {@link ApplicationRunner} that will run the Coherence {@link DefaultCacheServer}
	 */
	@Bean
 	public ApplicationRunner runCoherence(ApplicationContext ctx) {
		return (args) -> {
			LOGGER.info("Starting Coherence DefaultCacheServer");
			// Create the default ConfigurableCacheFactory
			ConfigurableCacheFactory ccf = CacheFactory.getConfigurableCacheFactory();
			// Store the Spring ApplicationContext into the ConfigurableCacheFactory resource registry
			// so that it is accessible later - for example when creating the CacheStore
			ccf.getResourceRegistry().registerResource(ApplicationContext.class, ctx);
	        // Start Coherence server
			DefaultCacheServer.startServerDaemon(ccf);
		};
	}
}
