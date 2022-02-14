package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap();

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
        return storage.values().toArray(new Resume[storage.size()]);
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void updateResume(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void addResume(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void removeResume(String uuid) {
        storage.remove(uuid);
    }

    @Override
    final protected void checkExistIndex(String uuid) {
        if (!storage.containsKey(uuid)) {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    final protected void checkNoExistIndex(String uuid) {
        if (storage.containsKey(uuid)) {
            throw new ExistStorageException(uuid);
        }
    }
}
