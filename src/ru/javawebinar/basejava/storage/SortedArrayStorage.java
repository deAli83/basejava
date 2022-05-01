package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

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
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "none");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}