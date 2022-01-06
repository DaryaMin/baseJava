package com.uirise.webapp.storage;

import com.uirise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage  extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("INFO: All resume deleted successful");
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) == -1) {
            if (size < storage.length) {
                storage[size] = r;
                size++;
                System.out.println("INFO: Resume with uuid " + r + " saved successful");
            } else {
                System.out.println("ERROR: Array size exceeded");
            }
        } else {
            System.out.println("ERROR: Resume with uuid = " + r + " is already exist");
        }
    }

    public void update(Resume resume) {
        int indexResume = getIndex(resume.getUuid());
        if (indexResume != -1) {
            storage[indexResume] = resume;
            System.out.println("INFO: Resume with uuid " + resume + " update successful");
        } else {
            System.out.println("ERROR: Resume with uuid " + resume + " not updated");
        }
    }

    public void delete(String uuid) {
        int indexResume = getIndex(uuid);
        if (indexResume != -1) {
            storage[indexResume] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("INFO: Resume with uuid " + uuid + " delete successful");
        } else {
            System.out.println("ERROR: Resume with uuid " + uuid + " not deleted");
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
