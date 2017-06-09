package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
<<<<<<< HEAD
    private Resume[] storage = new Resume[10000];
    private int size;
=======

    private static final int STORAGE_LIMIT = 10000;

    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;
>>>>>>> 9d8464c (Lesson03 equals&hashCode, static)

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        }
    }

    public void save(Resume r) {
<<<<<<< HEAD
        if (size < storage.length && findIndex(r.getUuid()) == -1) {
=======
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
>>>>>>> 9d8464c (Lesson03 equals&hashCode, static)
            storage[size] = r;
            size++;
            return;
        }
        System.out.println("Array is full");
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
        }
    }

<<<<<<< HEAD
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
=======
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
>>>>>>> 9d8464c (Lesson03 equals&hashCode, static)
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.out.println("Resume " + uuid + " find in storage");
                return i;
            }
        }
        System.out.println("Resume " + uuid + " is absent");
        return -1;
    }
}
