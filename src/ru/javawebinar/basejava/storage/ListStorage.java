package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(getIndex(uuid));
    }

    @Override
    protected void updateResume(Resume r) {
        storage.set(getIndex(r.getUuid()), r);
    }

    @Override
    protected void addResume(Resume r) {
        storage.add(r);
    }

    @Override
    protected void removeResume(String uuid) {
        storage.remove(getIndex(uuid));
    }

    protected int getIndex(String uuid) {
        for (Resume r : storage) {
            if (uuid.equals(r.getUuid())) {
                return storage.indexOf(r);
            }
        }
        return -1;
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
}
