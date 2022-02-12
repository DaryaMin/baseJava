package com.uirise.webapp.storage;

import com.uirise.webapp.exception.StorageException;
import com.uirise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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
    public void doSave(Resume r, Integer searchKey) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        addInArray(r, searchKey);
        size++;
        System.out.println("INFO: Resume with uuid = " + r + " saved successful");
    }

    @Override
    protected void doUpdate(Resume resume, Integer searchKey) {
        storage[searchKey] = resume;
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected void doDelete(Integer searchKey) {
        removeFromArray(searchKey);
        storage[size] = null;
        size--;
    }

    @Override
    public List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    protected abstract void removeFromArray(int index);

    protected abstract void addInArray(Resume r, int index);

}