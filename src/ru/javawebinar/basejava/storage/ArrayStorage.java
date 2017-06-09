package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        }
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            storage[size] = r;
            size++;
            return;
        }
        System.out.println("Array is full");
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);

    }

    protected int getIndex(String uuid) {
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
