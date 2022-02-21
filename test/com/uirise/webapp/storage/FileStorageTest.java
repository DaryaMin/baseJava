package com.uirise.webapp.storage;

import com.uirise.webapp.storage.strategy.StreamStrategy;

public class FileStorageTest  extends AbstractStorageTest {

    public FileStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new StreamStrategy()));
    }
}