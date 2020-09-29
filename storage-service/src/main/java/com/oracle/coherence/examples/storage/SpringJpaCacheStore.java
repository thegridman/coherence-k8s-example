package com.oracle.coherence.examples.storage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.tangosol.net.cache.CacheStore;

import org.springframework.data.repository.CrudRepository;

/**
 * A generic Spring JPA {@link CacheStore} that uses a Spring
 * {@link CrudRepository} for the database operations.
 *
 * @author Jonathan Knight  2020.09.29
 */
public class SpringJpaCacheStore<K, V>
        implements CacheStore<K, V> {

    /**
     * The Spring {@link CrudRepository}
     * to use to access the database.
     */
    private final CrudRepository<V, K> repository;

    /**
     * Create a {@link SpringJpaCacheStore} using the specified
     * {@link CrudRepository}.
     * 
     * @param repository the {@link CrudRepository} to use
     */
    public SpringJpaCacheStore(CrudRepository<V, K> repository) {
        this.repository = repository;
    }

    @Override
    public void erase(K key) {
        repository.deleteById(key);
    }

    @Override
    public V load(K key) {
        return repository.findById(key).orElse(null);
    }

    @Override
    public void store(K key, V value) {
        repository.save(value);
    }

    @Override
    public Map<K, V> loadAll(Collection<? extends K> colKeys) {
        Map<K, V> map = new HashMap<>();
        for (K key : colKeys) {
            V value = load(key);
            if (value != null) {
                map.put(key, value);
            }
        }
        return map;
    }

    @Override
    public void eraseAll(Collection<? extends K> colKeys) {
        boolean fRemove = true;

        for (Iterator<? extends K> iter = colKeys.iterator(); iter.hasNext(); ) {
            erase(iter.next());
            if (fRemove) {
                try {
                    iter.remove();
                }
                catch (UnsupportedOperationException e) {
                    fRemove = false;
                }
            }
        }
    }

    @Override
    public void storeAll(Map<? extends K, ? extends V> mapEntries) {
        boolean fRemove = true;

        for (Iterator<? extends Map.Entry<? extends K, ? extends V>> iter = mapEntries.entrySet().iterator();
                iter.hasNext(); ) {
            Map.Entry<? extends K, ? extends V> entry = iter.next();
            store(entry.getKey(), entry.getValue());
            if (fRemove) {
                try {
                    iter.remove();
                }
                catch (UnsupportedOperationException e) {
                    fRemove = false;
                }
            }
        }
    }
}
