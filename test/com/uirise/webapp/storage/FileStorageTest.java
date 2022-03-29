package com.uirise.webapp.storage;

import com.uirise.webapp.storage.strategy.ObjectStreamSerializer;

public class FileStorageTest  extends AbstractStorageTest {

    public FileStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new ObjectStreamSerializer()));
    }
}