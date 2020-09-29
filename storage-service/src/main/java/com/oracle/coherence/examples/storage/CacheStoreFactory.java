package com.oracle.coherence.examples.storage;

import com.tangosol.net.BackingMapManagerContext;
import com.tangosol.net.cache.CacheStore;
import com.tangosol.util.ResourceRegistry;

import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;

/**
 * A generic factory to create {@link SpringJpaCacheStore} instances.
 * <p>
 * There must be a Spring bean of type {@link CrudRepository} with the
 * same name as the cache name.
 *
 * @author Jonathan Knight  2020.09.09
 */
public class CacheStoreFactory {

    /**
     * Create a {@link CacheStore} for a given cache name.
     * <p>
     * A Spring bean of type {@link CrudRepository} will be looked up from the Spring
     * context using the cache name as the bean name. This repository will be used
     * by a generic {@link SpringJpaCacheStore} to access the database.
     *
     * @param cacheName  the name of the cache
     * @param bmCtx      the Coherence cache {@link BackingMapManagerContext}
     *
     * @return a {@link CacheStore} for a the cache.
     */
    public static CacheStore<?, ?> createCacheStore(String cacheName, BackingMapManagerContext bmCtx) {
        ResourceRegistry     registry   = bmCtx.getManager().getCacheFactory().getResourceRegistry();
        ApplicationContext   ctx        = registry.getResource(ApplicationContext.class);
        CrudRepository<?, ?> repository = ctx.getBean(cacheName, CrudRepository.class);

        return new SpringJpaCacheStore<>(repository);
    }
}
