package com.uirise.webapp.storage;

import com.uirise.webapp.exception.StorageException;
import com.uirise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected int size = 0;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("INFO: All resume deleted successful");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void doSave(Resume r) {
        if (size < storage.length) {
            addInStorage(r, getIndex(r.getUuid()));
            size++;
            System.out.println("INFO: Resume with uuid = " + r + " saved successful");
        } else {
            throw new StorageException("Storage overflow", r.getUuid());
        }
    }

    protected abstract int getIndex(String uuid);

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage[getIndex(searchKey.toString())] = resume;
    }

    @Override
    protected Resume doGet(String uuid) {
        return storage[getIndex(uuid)];
    }

    @Override
    protected void doDelete(String uuid) {
        removeFromStorage(getIndex(uuid));
        storage[size] = null;
        size--;
    }

    @Override
    public List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return getIndex(searchKey.toString()) >= 0;
    }

    protected abstract void removeFromStorage(int index);

    protected abstract void addInStorage(Resume r, int index);

}