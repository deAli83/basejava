package com.unrise.webapp.storage;

import com.unrise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        if (checkUuid(resume.getUuid()) != -1) {
            storage[checkUuid(resume.getUuid())] = resume;
        }
    }

    public void save(Resume r) {
        if (size < 9999 && checkUuid(r.getUuid()) == -1) {
            storage[size] = r;
            size++;
            return;
        }
        System.out.println("Array is full");
    }

    public Resume get(String uuid) {
        if (checkUuid(uuid) != -1) {
            return storage[checkUuid(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        if (checkUuid(uuid) != -1) {
            storage[checkUuid(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int checkUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        System.out.println("Resume is absent");
        return -1;
    }
}
