package com.uirise.webapp.storage;

import com.uirise.webapp.model.Resume;

import java.awt.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;

public class ListStorage extends AbstractStorage implements Storage {
    ArrayList<Resume> storage = new ArrayList<>();

    @Override
    protected Resume get(int index) {
        return storage.get(index);
    }

    @Override
    protected void delete(int index) {
        storage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    protected void save(Resume r, int index) {
        storage.add(r);
    }

    @Override
    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected void update(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    public void clear() {
        storage.removeAll(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }
}