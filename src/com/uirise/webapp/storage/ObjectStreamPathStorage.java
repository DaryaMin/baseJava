package com.uirise.webapp.storage;

import com.uirise.webapp.exception.StorageException;
import com.uirise.webapp.model.Resume;

import java.io.*;

public class ObjectStreamPathStorage extends AbstractPathStorage{
    protected ObjectStreamPathStorage(String dir) {
        super(dir);
    }
    @Override
    protected void doWrite(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}