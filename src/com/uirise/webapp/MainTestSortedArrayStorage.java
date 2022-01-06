package com.uirise.webapp;

import com.uirise.webapp.model.Resume;
import com.uirise.webapp.storage.ArrayStorage;
import com.uirise.webapp.storage.SortedArrayStorage;
import com.uirise.webapp.storage.Storage;

/**
 * Test for your com.uirise.webapp.storage.ArrayStorage implementation
 */
public class MainTestSortedArrayStorage {
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid2");
        Resume r2 = new Resume();
        r2.setUuid("uuid8");
        Resume r3 = new Resume();
        r3.setUuid("uuid1");
        Resume r4 = new Resume();
        r4.setUuid("uuid4");
        Resume r5 = new Resume();
        r5.setUuid("uuid9");


        ARRAY_STORAGE.save(r1);
        printAll();
        ARRAY_STORAGE.save(r2);
        printAll();
        ARRAY_STORAGE.save(r3);
        printAll();
        ARRAY_STORAGE.save(r4);
        printAll();
        ARRAY_STORAGE.save(r5);
        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.update(r3);//update existing resume
        printAll();
        ARRAY_STORAGE.update(r4);//update nonexistent resume
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("Get All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
        System.out.println("\n");
    }
}
