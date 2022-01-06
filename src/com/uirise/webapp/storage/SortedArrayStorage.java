package com.uirise.webapp.storage;

import com.uirise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void save(Resume r){
        int index = getIndex(r.getUuid());
        if (index <= -1) {
            if (size < storage.length) {
                for (int i = size; i > -index-1; i--) {
                    storage[i] = storage[i-1];
                }
                storage[-index-1] = r;
                size++;
                System.out.println("INFO: Resume with uuid = " + r + " saved successful");
            } else {
                System.out.println("ERROR: Array size exceeded");
            }
        } else {
            System.out.println("ERROR: Resume with uuid = " + r + " is already exist");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            for (int i = index; i < size; i++) {
                storage[i] = storage[i+1];
            }
            size--;
            System.out.println("INFO: Resume with uuid = " + uuid + " delete successful");
        } else {
            System.out.println("ERROR: Resume with uuid = " + uuid + " not deleted");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}