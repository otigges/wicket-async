package com.innoq.samples.cache;

import java.io.Serializable;

public class CacheKey<T> implements Serializable {

    private final Class<T> entityClass;

    private final String id;

    // ----------------------------------------------------

    public static <T> CacheKey<T> from(Class<T> entityClass, String id) {
        return new CacheKey<T>(entityClass, id);
    }

    // ----------------------------------------------------

    public CacheKey(Class<T> entityClass, String id) {
        this.entityClass = entityClass;
        this.id = id;
    }

    // ----------------------------------------------------

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public String getId() {
        return id;
    }

    // ----------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheKey)) return false;

        CacheKey cacheKey = (CacheKey) o;

        if (entityClass != null ? !entityClass.equals(cacheKey.entityClass) : cacheKey.entityClass != null)
            return false;
        if (id != null ? !id.equals(cacheKey.id) : cacheKey.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = entityClass != null ? entityClass.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CacheKey{" +
                "entityClass=" + entityClass +
                ", id='" + id + '\'' +
                '}';
    }

}
