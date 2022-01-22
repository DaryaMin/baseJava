package com.uirise.webapp.storage;

import com.uirise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void addInStorage(Resume r, int index) {
        index = -index - 1;
        if (size - index >= 0) System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    public void removeFromStorage(int index) {
        if (size - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid, null);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}