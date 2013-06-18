package com.innoq.samples.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class ModelEhCache implements ModelCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelEhCache.class);

    private final Cache cache;

    // ----------------------------------------------------

    public ModelEhCache(Resource cacheConfig) throws IOException {
        cache = CacheManager.newInstance(cacheConfig.getURL()).getCache("wasnc-cache");
    }

    // ----------------------------------------------------

    @Override
    public void put(CacheKey<?> key, Object entity) {
        Element element = new Element(key, entity);
        cache.put(element);
        LOGGER.debug("Cached element {}", key);
    }

    @Override
    public <T> T get(CacheKey<T> key) {
        Element element = cache.get(key);
        if (element != null) {
            LOGGER.debug("Cache hit for {}", key);
            return (T) element.getObjectValue();
        } else {
            LOGGER.debug("Cache miss for {}", key);
            return null;
        }
    }

    @Override
    public boolean contains(CacheKey key) {
        return cache.get(key) != null;
    }

    // ----------------------------------------------------

    @Override
    public double getCacheSize() {
        return cache.calculateInMemorySize() / 1024.;
    }

}
