package com.uirise.webapp.storage;

import com.uirise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage implements Storage {
    private  List<Resume> storage = new ArrayList<>();

    @Override
    protected Resume getByIndex(int index) {
        return storage.get(index);
    }

    @Override
    protected void deleteByIndex(int index) {
        storage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    protected void saveByIndex(Resume r, int index) {
        storage.add(r);
    }

    @Override
    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected void updateByIndex(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}