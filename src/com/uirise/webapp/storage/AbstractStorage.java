package com.uirise.webapp.storage;

import com.uirise.webapp.exception.ExistStorageException;
import com.uirise.webapp.exception.NotExistStorageException;
import com.uirise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {
    protected abstract SK getSearchKey(String uuid);

    public void update(Resume resume) {
        SK searchKey = getSearchKeyIfExist(resume.getUuid());
        doUpdate(resume, searchKey);
        System.out.println("INFO: Resume with uuid = " + resume + " update successful");
    }

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    public void save(Resume resume) {
        SK searchKey = getSearchKeyIfNotExist(resume.getUuid());
        doSave(resume, searchKey);
    }

    protected abstract void doSave(Resume r, SK searchKey);

    public Resume get(String uuid) {
        SK searchKey = getSearchKeyIfExist(uuid);
        return doGet(searchKey);
    }

    protected abstract Resume doGet(SK searchKey);

    public void delete(String uuid) {
        SK searchKey = getSearchKeyIfExist(uuid);
        doDelete(searchKey);
        System.out.println("INFO: Resume with uuid = " + uuid + " delete successful");
    }

    public List<Resume> getAllSorted() {
        Comparator<Resume> resumeComparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        List<Resume> list = doGetAll();
        list.sort(resumeComparator);

        return list;
    }

    protected abstract List<Resume> doGetAll();

    protected abstract void doDelete(SK searchKey);

    private SK getSearchKeyIfExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getSearchKeyIfNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

}
