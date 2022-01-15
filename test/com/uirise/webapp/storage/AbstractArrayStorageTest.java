package com.uirise.webapp.storage;

import com.uirise.webapp.exception.ExistStorageException;
import com.uirise.webapp.exception.NotExistStorageException;
import com.uirise.webapp.exception.StorageException;
import com.uirise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static com.uirise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {

    private Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void updateCorrect() throws Exception {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        Assert.assertEquals(storage.get(UUID_1), newResume);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume(UUID_4));
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertEquals(storage.size(), storage.getAll().length);
    }

    @Test
    public void saveCorrect() throws Exception {
        storage.save(new Resume(UUID_4));
        Assert.assertEquals(storage.size(), 4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume(UUID.randomUUID().toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Переполнение произошло раньше времени");
        }
        storage.save(new Resume(UUID_4));
    }

    @Test
    public void deleteExist() throws Exception {
        storage.delete(UUID_1);
        Assert.assertEquals(storage.size(), 2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(storage.get(UUID_1), new Resume(UUID_1));
    }
}