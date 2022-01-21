package com.uirise.webapp.storage;

import com.uirise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateByIndex(Resume resume, int index) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void saveByIndex(Resume r, int index) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected int getIndex(String uuid) {
        int index = -1;
        for (Object key : storage.keySet()) {
            index++;
            if (key.equals(uuid)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    protected Resume getByIndex(int index) {
        int i = 0;
        for (Object key : storage.keySet()) {
            if (i == index) {
                return storage.get(key);
            }
            i++;
        }
        return null;
    }

    @Override
    protected void deleteByIndex(int index, String uuid) {
        storage.remove(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return Arrays.stream(storage.values().toArray(new Resume[storage.size()])).sorted().toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
