package com.uirise.webapp.storage;

import com.uirise.webapp.storage.strategy.FileStorageTest;
import com.uirise.webapp.storage.strategy.PathStorageTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class,
        ObjectStreamPathStorageTest.class,
        ObjectStreamStorageTest.class,
        PathStorageTest.class,
        FileStorageTest.class
})
public class AllStorageTest {
}
