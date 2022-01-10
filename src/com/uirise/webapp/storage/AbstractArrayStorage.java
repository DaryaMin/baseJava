package com.uirise.webapp.storage;

import com.uirise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("INFO: All resume deleted successful");
    }

    public int size() {
        return size;
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            if (size < storage.length) {
                addInStorage(r, index);
                size++;
                System.out.println("INFO: Resume with uuid = " + r + " saved successful");
            } else {
                System.out.println("ERROR: Array size exceeded");
            }
        } else {
            System.out.println("ERROR: Resume with uuid = " + r + " is already exist");
        }
    }

    public void update(Resume resume) {
        int indexResume = getIndex(resume.getUuid());
        if (indexResume > -1) {
            storage[indexResume] = resume;
            System.out.println("INFO: Resume with uuid = " + resume + " update successful");
        } else {
            System.out.println("ERROR: Resume with uuid = " + resume + " not updated");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            removeFromStorage(index);
            storage[size] = null;
            size--;
            System.out.println("INFO: Resume with uuid = " + uuid + " delete successful");
        } else {
            System.out.println("ERROR: Resume with uuid = " + uuid + " not deleted");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void removeFromStorage(int index);

    protected abstract void addInStorage(Resume r, int index);

    protected abstract int getIndex(String uuid);
}