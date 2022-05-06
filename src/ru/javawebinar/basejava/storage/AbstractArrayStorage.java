package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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
    final public List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    final protected Resume getResume(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    final public void updateResume(Resume r, Integer searchKey) {
        storage[searchKey] = r;
    }

    @Override
    final public void addResume(Resume r, Integer searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        size++;
        add(r, searchKey);
    }

    @Override
    final public void removeResume(Integer searchKey) {
        size--;
        deleteResume(searchKey);
        storage[size] = null;
    }

    @Override
    final protected boolean isExist(Integer searchKey) {
        return (searchKey) < 0;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void add(Resume r, int index);

    protected abstract void deleteResume(int index);
}
