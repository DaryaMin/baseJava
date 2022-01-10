package com.uirise.webapp.storage;

import com.uirise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void addInStorage(Resume r, int index) {
        for (int i = size; i > -index - 1; i--) {
            storage[i] = storage[i - 1];
        }
        storage[-index - 1] = r;
    }

    @Override
    public void removeFromStorage(int index) {
        for (int i = index; i < size; i++) {
            storage[i] = storage[i + 1];
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}