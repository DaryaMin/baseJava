package com.uirise.webapp.storage.strategy;

import com.uirise.webapp.storage.AbstractStorageTest;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new StreamStrategy()));
    }

}