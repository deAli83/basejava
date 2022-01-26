package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void add(Resume r) {
        int shift = size - (Math.abs(getIndex(r.getUuid())) + 1);
            if (shift == 0) {
                storage[size] = r;
            } else {
                for (int i = 1; i <= shift; i++) {
                    storage[size] = storage[size - i];
                }
                storage[size - shift] = r;
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