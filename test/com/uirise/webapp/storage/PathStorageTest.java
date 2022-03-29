package com.uirise.webapp.storage;

import com.uirise.webapp.storage.strategy.ObjectStreamSerializer;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new ObjectStreamSerializer()));
    }

}