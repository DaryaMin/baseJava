package com.uirise.webapp.storage;

import com.uirise.webapp.exception.StorageException;
import com.uirise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage implements Storage {
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
    public void save(Resume r, int index) {
        if (size < storage.length) {
            addInStorage(r, index);
            size++;
            System.out.println("INFO: Resume with uuid = " + r + " saved successful");
        } else {
            throw new StorageException("Storage overflow", r.getUuid());
        }
    }

    @Override
    protected void update(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected Resume get(int index) {
        return storage[index];
    }

    @Override
    protected void delete(int index) {
        removeFromStorage(index);
        storage[size] = null;
        size--;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void removeFromStorage(int index);

    protected abstract void addInStorage(Resume r, int index);

}