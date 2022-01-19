package com.uirise.webapp.storage;

import com.uirise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void addInStorage(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    public void removeFromStorage(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        System.out.println("INFO: Resume with uuid = " + uuid + " not exist");
        return -1;
    }
}

