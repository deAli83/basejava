package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
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
    final protected Resume getResume(String uuid) {
        return storage[getIndex(uuid)];
    }

    @Override
    final public void updateResume(Resume r) {
        storage[getIndex(r.getUuid())] = r;
    }

    @Override
    final public void addResume(Resume r) {
        String uuid = r.getUuid();
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", uuid);
        }
        size++;
        add(r);
    }

    @Override
    final public void removeResume(String uuid) {
        size--;
        deleteResume(getIndex(uuid));
        storage[size] = null;
    }

    @Override
    final protected void checkExistIndex(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    final protected void checkNoExistIndex(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void add(Resume r);

    protected abstract void deleteResume(int index);
}
