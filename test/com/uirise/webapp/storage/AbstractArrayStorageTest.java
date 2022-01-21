package com.uirise.webapp.storage;

import com.uirise.webapp.exception.StorageException;
import com.uirise.webapp.model.Resume;
import org.junit.Test;

import java.util.UUID;

import static com.uirise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume(UUID.randomUUID().toString()));
            }
        } catch (StorageException se) {
            fail("Переполнение произошло раньше времени");
        }
        storage.save(new Resume("uuid1"));
    }
}