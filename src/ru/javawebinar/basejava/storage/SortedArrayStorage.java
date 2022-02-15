package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void add(Resume r, int index) {
        index = -index - 1;
        int shift = size - (index + 1);
        System.arraycopy(storage, index, storage, index + 1, shift);
        storage[index] = r;
    }

    @Override
    protected void deleteResume(int index) {
        int shift = size - index;
        System.arraycopy(storage, index + 1, storage, index, shift);
    }

    @Override
    protected Object getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}