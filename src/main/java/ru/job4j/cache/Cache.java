package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (k, r) -> {
            if (memory.get(k).getVersion() != r.getVersion()) {
                try {
                    throw new OptimisticException("Versions are not equal");
                } catch (OptimisticException e) {
                    throw new RuntimeException(e);
                }
            }
            return new Base(k, r.getVersion() + 1);
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public int size() {
        return memory.size();
    }
}