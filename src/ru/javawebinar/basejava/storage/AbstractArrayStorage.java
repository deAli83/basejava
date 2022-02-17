package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    final public int size() {
        return size;
    }

    @Override
    final public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    final public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    final protected Resume getResume(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    final public void updateResume(Resume r, Object searchKey) {
        storage[(Integer) searchKey] = r;
    }

    @Override
    final public void addResume(Resume r, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        size++;
        add(r, (Integer) searchKey);
    }

    @Override
    final public void removeResume(Object searchKey) {
        size--;
        deleteResume((Integer) searchKey);
        storage[size] = null;
    }

    @Override
    final protected boolean isExist(Object searchKey) {
        return ((Integer) searchKey) < 0;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void add(Resume r, int index);

    protected abstract void deleteResume(int index);
}
