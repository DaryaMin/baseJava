package com.uirise.webapp.storage;

import com.uirise.webapp.exception.ExistStorageException;
import com.uirise.webapp.exception.NotExistStorageException;
import com.uirise.webapp.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected abstract Object getSearchKey(String uuid);

    public void update(Resume resume) {
        Object searchKey = notExistCheck(resume.getUuid());
        doUpdate(resume, searchKey);
        System.out.println("INFO: Resume with uuid = " + resume + " update successful");
    }

    private Object notExistCheck(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object existCheck(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    public void save(Resume resume) {
        Object searchKey = existCheck(resume.getUuid());
        doSave(resume, searchKey);
    }

    protected abstract void doSave(Resume r, Object searchKey);

    public Resume get(String uuid) {
        Object searchKey = notExistCheck(uuid);
        return doGet(searchKey);
    }

    protected abstract Resume doGet(Object searchKey);

    public void delete(String uuid) {
        Object searchKey = notExistCheck(uuid);
        doDelete(searchKey);
        System.out.println("INFO: Resume with uuid = " + uuid + " delete successful");
    }

    public List<Resume> getAllSorted() {
        Comparator<Resume> resumeComparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        List<Resume> list = doGetAll();
        Collections.sort(list, resumeComparator);

        return list;
    }

    protected abstract List<Resume> doGetAll();

    protected abstract void doDelete(Object searchKey);
}
