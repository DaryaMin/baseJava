package com.uirise.webapp.storage;

import com.uirise.webapp.model.Resume;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(searchKey.toString());
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage.replace(searchKey.toString(), resume);
    }

    @Override
    protected void doSave(Resume r) {
        storage.put(r.getUuid(), r);
    }


//    protected Integer getIndex(String uuid) {
//        int index = -1;
//        for (Object key : storage.keySet()) {
//            index++;
//            if (key.equals(uuid)) {
//                return index;
//            }
//        }
//        return null;
//    }

    @Override
    protected Resume doGet(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void doDelete(String uuid) {
        storage.remove(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> doGetAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
