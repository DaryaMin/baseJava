package com.uirise.webapp.sql;

import com.uirise.webapp.Config;
import com.uirise.webapp.storage.AbstractStorageTest;

import static org.junit.Assert.*;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}