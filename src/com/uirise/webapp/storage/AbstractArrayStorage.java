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

    public abstract void save(Resume r);

    public abstract void delete(String uuid);

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);
}