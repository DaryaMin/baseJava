package com.uirise.webapp.storage;

import com.uirise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (!isResumeExist(r.toString())) {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("ERROR: Array size exceeded");
            }
        } else {
            System.out.println("ERROR: Resume whith uuid = " + r.toString() + " is already exist");
        }

    }

    public void update(Resume resume) {
        if (isResumeExist(resume.getUuid())) {
            for (int i = 0; i < size; i++) {
                if (storage[i].equals(resume)) {
                    storage[i] = resume;
                }
            }
        } else {
            save(resume);
        }
    }

    public Resume get(String uuid) {
        if (isResumeExist(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
            System.out.println("ERROR: Resume whith uuid = " + uuid + " not exist");
        }
        return null;
    }

    public void delete(String uuid) {
        if (isResumeExist(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString() == uuid) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    break;
                }
            }
        } else {
            System.out.println("ERROR: Resume whith uuid = " + uuid + " not exist");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private boolean isResumeExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return true;
            }
        }
        return false;
    }


}
