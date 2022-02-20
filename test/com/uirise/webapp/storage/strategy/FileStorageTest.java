package com.uirise.webapp.storage.strategy;

import com.uirise.webapp.storage.AbstractStorageTest;

public class FileStorageTest  extends AbstractStorageTest {

    public FileStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new StreamStrategy()));
    }
}