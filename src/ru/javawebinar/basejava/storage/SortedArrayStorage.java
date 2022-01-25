package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume " + r.getUuid() + " not exist");
            return;
        } else {
            storage[index] = r;
        }
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
            return;
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
            return;
        } else {
            size++;
            int shift = size - Math.abs(index);
            if (shift == 0) {
                storage[size - 1] = r;
            } else {
                for (int i = 1; i <= shift; i++) {
                    storage[size - i] = storage[size - (i + 1)];
                }
                storage[size - (shift + 1)] = r;
            }
            System.out.println("Size " + size);
            return;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            size--;
            int shift = size - index;
            for (int i = 0; i < shift; i++) {
                storage[index + i] = storage[index + (i + 1)];
            }
            storage[size] = null;
        } else {
            System.out.println("Resume " + uuid + " not exist");
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        System.out.println(uuid + Arrays.binarySearch(storage, 0, size, searchKey));
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}