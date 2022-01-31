package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.SortedArrayStorage;

/**
 * Test for your com.ru.javawebinar.basejava.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();
    static final SortedArrayStorage SORT_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("uuid4");

        System.out.println("ARRAY_STORAGE");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());
        ARRAY_STORAGE.update(r2);

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        ARRAY_STORAGE.update(r4);

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size() + "\n");

        System.out.println("SORT_ARRAY_STORAGE");

        SORT_ARRAY_STORAGE.save(r2);
        SORT_ARRAY_STORAGE.save(r3);
        SORT_ARRAY_STORAGE.save(r1);

        System.out.println("Get r1: " + SORT_ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + SORT_ARRAY_STORAGE.size());
        SORT_ARRAY_STORAGE.update(r2);

        System.out.println("Get dummy: " + SORT_ARRAY_STORAGE.get("dummy"));
        SORT_ARRAY_STORAGE.update(r4);

        printAll();
        SORT_ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        SORT_ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + SORT_ARRAY_STORAGE.size());

    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }

        for (Resume r : SORT_ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
