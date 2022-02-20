package com.uirise.webapp.storage.strategy;

import com.sun.xml.internal.txw2.output.StreamSerializer;
import com.uirise.webapp.exception.StorageException;
import com.uirise.webapp.model.Resume;
import com.uirise.webapp.storage.AbstractStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private File directory;

    private StreamStrategy streamStrategy;

    protected FileStorage(File directory, StreamStrategy streamStrategy) {
        Objects.requireNonNull(directory, "directory must not be null");
        this.streamStrategy = streamStrategy;
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                doDelete(file);
            }
        }
    }

    @Override
    public int size() {
        String[] listFile = directory.list();
        if (listFile != null) {
            return listFile.length;
        }
        return 0;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            streamStrategy.doWrite(r,  new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            streamStrategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return streamStrategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File does not deleted", file.getName());
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        List<Resume> resumeList = new ArrayList<>();
        File[] files= directory.listFiles();
        if (files != null) {
            for (File file: files) {
                resumeList.add(doGet(file));
            }
        }
        return resumeList;
    }
}

