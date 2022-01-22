package com.uirise.webapp.storage;

import com.uirise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage implements Storage {
    private  List<Resume> storage = new ArrayList<>();

    @Override
    protected Resume doGet(String uuid) {
        return storage.get(getIndex(uuid));
    }

    @Override
    protected void doDelete(String uuid) {
        storage.remove(getIndex(uuid).intValue());
        System.out.println(storage.size());
    }

    @Override
    public List<Resume> doGetAll() {
        return new ArrayList<>(storage);
    }

    @Override
    protected void doSave(Resume r) {
        storage.add(r);
    }

//    @Override
//    protected int getIndex(String uuid) {
//        int index = -1;
//        for (Resume r : storage) {
//            index++;
//            if (Objects.equals(r.getUuid(), uuid)) {
//                return index;
//            }
//        }
//        return -1;
//    }

    @Override
    protected boolean isExist(Object searchKey) {
        return getIndex(searchKey.toString()) != null;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage.add(resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    protected Integer getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}