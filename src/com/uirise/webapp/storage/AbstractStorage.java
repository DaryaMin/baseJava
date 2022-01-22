package com.uirise.webapp.storage;

import com.uirise.webapp.exception.ExistStorageException;
import com.uirise.webapp.exception.NotExistStorageException;
import com.uirise.webapp.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    public void update(Resume resume) {
        if (isExist(resume.getUuid())) {
            doUpdate(resume, resume.getUuid());
            System.out.println("INFO: Resume with uuid = " + resume + " update successful");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    public void save(Resume r) {
        if (isExist(r.getUuid())) {
            throw new ExistStorageException(r.getUuid());
        }
        doSave(r);
    }

    protected abstract void doSave(Resume r);

 //   protected abstract Integer getIndex(String uuid);

    public Resume get(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(uuid);
    }

    protected abstract Resume doGet(String searchKey);

    public void delete(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        doDelete(uuid);
        System.out.println("INFO: Resume with uuid = " + uuid + " delete successful");
    }

    public List<Resume> getAllSorted() {
        Comparator<Resume> resumeComparator = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        List<Resume> list = doGetAll();
        Collections.sort(list, resumeComparator);

        return list;
    }

//    public List<Resume> getAllSorted2() {
//        List<Resume> list = doGetAll();
//        Collections.sort(list, new Comparator<Resume>() {
//            @Override
//            public int compare(Resume p1, Resume p2)
//            {
//                if (p1.getUuid() != p2.getUuid()) {
//                    return p1.getUuid().compareTo(p2.getUuid());
//                }
//                return p1.getFullName().compareTo(p2.getFullName());
//            }
//        });
//        return list;
//    }

    protected abstract List<Resume> doGetAll();

    protected abstract void doDelete(String uuid);
}
