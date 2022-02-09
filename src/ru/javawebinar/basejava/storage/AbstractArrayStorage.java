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
    final protected Resume getResume(int index) {
        return storage[index];
    }

    @Override
    final public void updateResume(Resume r, int index) {
        storage[index] = r;
    }

    @Override
    final public void addResume(Resume r, int index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        size++;
        add(r, index);
    }

    @Override
    final public void removeResume(String uuid, int index) {
        size--;
        deleteResume(index);
        storage[size] = null;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void add(Resume r, int index);

    protected abstract void deleteResume(int index);
}
