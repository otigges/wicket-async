package com.innoq.samples.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class ModelCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelCache.class);
    private static final String WASNC_CACHE = "wasnc-cache";

    private final CacheManager cacheManager;
    private final Cache cache;

    // ----------------------------------------------------

    public ModelCache() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("ehcache.xml");
        cacheManager = CacheManager.newInstance(url);
        cache = cacheManager.getCache(WASNC_CACHE);

        LOGGER.info("Initialized ehcache: {}", cache);
    }

    // ----------------------------------------------------

    public void put(CacheKey<?> key, Object entity) {
        Element element = new Element(key, entity);
        cache.put(element);
        LOGGER.debug("Cached element {}", key);
    }

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

    public boolean contains(CacheKey key) {
        return cache.get(key) != null;
    }

    // ----------------------------------------------------

    public double getCacheSize() {
        return cache.calculateInMemorySize() / 1024.;
    }

}
