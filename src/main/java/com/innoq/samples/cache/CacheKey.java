package com.innoq.samples.cache;

import java.io.Serializable;

public class CacheKey<T> implements Serializable {

    private final String type;

    private final String id;

    // ----------------------------------------------------

    public static <T> CacheKey<T> from(Class<T> entityClass, String id) {
        return new CacheKey<T>(entityClass.getSimpleName(), id);
    }

    public static <T> CacheKey<T> from(String type, String id) {
        return new CacheKey<T>(type, id);
    }

    // ----------------------------------------------------

    public CacheKey(String type, String id) {
        this.type = type;
        this.id = id;
    }

    // ----------------------------------------------------

    public String getType() {
        return type;
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

        if (type != null ? !type.equals(cacheKey.type) : cacheKey.type != null)
            return false;
        if (id != null ? !id.equals(cacheKey.id) : cacheKey.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CacheKey{" +
                "type=" + type +
                ", id='" + id + '\'' +
                '}';
    }

}
