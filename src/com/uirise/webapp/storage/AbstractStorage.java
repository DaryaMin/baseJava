package com.uirise.webapp.storage;

import com.uirise.webapp.exception.ExistStorageException;
import com.uirise.webapp.exception.NotExistStorageException;
import com.uirise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public abstract void clear();

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            update(resume, index);
            System.out.println("INFO: Resume with uuid = " + resume + " update successful");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract void update(Resume resume, int index);

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            save(r, index);
        }
    }

    protected abstract void save(Resume r, int index);

    protected abstract int getIndex(String uuid);

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return get(index);
    }

    protected abstract Resume get(int index);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            delete(index);
            System.out.println("INFO: Resume with uuid = " + uuid + " delete successful");
        }
    }

    protected abstract void delete(int index);

    public abstract Resume[] getAll();

    public abstract int size();
}
