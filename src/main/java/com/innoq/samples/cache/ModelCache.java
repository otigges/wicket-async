package com.innoq.samples.cache;

/**
 * <p>
 * DESCRITPION.
 * </p>
 * <p/>
 * <p>
 * Created 18.06.13
 * </p>
 *
 * @author Oliver Tigges
 */
public interface ModelCache {
    void put(CacheKey<?> key, Object entity);

    <T> T get(CacheKey<T> key);

    boolean contains(CacheKey key);

    double getCacheSize();
}
