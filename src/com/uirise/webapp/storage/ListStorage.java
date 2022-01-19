package com.uirise.webapp.storage;

import com.uirise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage implements Storage {
    private  Collection<Resume> storage = new ArrayList<>();

    @Override
    protected Resume getByIndex(int index) {
        Iterator<Resume> iterator = storage.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if (i == index) {
                return resume;
            }
            i++;
        }
        return null;
    }

    @Override
    protected void deleteByIndex(int index) {
        Iterator<Resume> iterator = storage.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (i == index) {
                iterator.remove();
            }
            i++;
        }
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
        Iterator<Resume> iterator = storage.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if (Objects.equals(resume.getUuid(), uuid)) {
                return i;
            }
            i++;
        }
        return -1;
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