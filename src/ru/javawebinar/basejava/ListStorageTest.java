package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ListStorage;

/**
 * Test for your com.ru.javawebinar.basejava.storage.ArrayStorage implementation
 */
public class ListStorageTest {
    static final ListStorage LIST_STORAGE = new ListStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "name1");
        Resume r2 = new Resume("uuid2", "name2");
        Resume r3 = new Resume("uuid3", "name3");
        Resume r4 = new Resume("uuid4", "name4");

        LIST_STORAGE.save(r1);
        LIST_STORAGE.save(r2);
        LIST_STORAGE.save(r3);

        System.out.println("Get r1: " + LIST_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + LIST_STORAGE.size());
        LIST_STORAGE.update(r2);

        printAll();
        LIST_STORAGE.delete(r1.getUuid());
        printAll();
        LIST_STORAGE.clear();
        printAll();

        System.out.println("Size: " + LIST_STORAGE.size() + "\n");
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : LIST_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
