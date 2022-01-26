package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void add(Resume r) {
        int shift = size + 1 - Math.abs(getIndex(r.getUuid()));
            if (shift == 0) {
                storage[size] = r;
            } else {
                for (int i = 0; i < shift; i++) {
                    storage[size - i] = storage[size - (i + 1)];
                }
                storage[size - shift] = r;
            }
        }

    @Override
    protected void deleteResume(int index) {
        int shift = size - index;
        for (int i = 0; i < shift; i++) {
            storage[index + i] = storage[index + (i + 1)];
        }
        storage[size] = null;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}