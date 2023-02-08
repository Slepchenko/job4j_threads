package ru.job4j.cache;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CacheTest {
    @Test
    public void whenAddElement() {
        Cache cache = new Cache();
        assertTrue(cache.add(new Base(1, 0)));
    }

    @Test
    public void whenDeleteElement() {
        Cache cache = new Cache();
        cache.add(new Base(1, 0));
        cache.add(new Base(2, 0));
        cache.delete(new Base(1, 0));
        assertEquals(1, cache.size());
    }

    @Test
    public void whenDoNotDeleteElement() {
        Cache cache = new Cache();
        cache.add(new Base(1, 0));
        cache.add(new Base(2, 0));
        cache.delete(new Base(3, 0));
        assertEquals(2, cache.size());
    }

    @Test
    public void whenUpdateElement() {
        Cache cache = new Cache();
        cache.add(new Base(1, 0));
        assertTrue(cache.update(new Base(1, 0)));
    }

    @Test
    public void whenDoNotUpdate() {
        Cache cache = new Cache();
        cache.add(new Base(1, 0));
        assertFalse(cache.update(new Base(2, 0)));
    }
}