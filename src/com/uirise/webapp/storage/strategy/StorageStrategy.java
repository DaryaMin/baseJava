package com.uirise.webapp.storage.strategy;

import com.uirise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StorageStrategy {
    void doWrite(Resume r, OutputStream os) throws IOException;
    Resume doRead(InputStream is) throws IOException;
}
