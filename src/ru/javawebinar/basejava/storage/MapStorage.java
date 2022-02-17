package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

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
    protected Resume getResume(Object uuid) {
        return storage.get((String) uuid);
    }

    @Override
    public void updateResume(Resume r, Object uuid) {
        storage.put((String) uuid, r);
    }

    @Override
    protected void addResume(Resume r, Object uuid) {
        storage.put((String) uuid, r);
    }

    @Override
    protected void removeResume(Object uuid) {
        storage.remove((String) uuid);
    }

    @Override
    final protected boolean isExist(Object uuid) {
        return !storage.containsKey((String) uuid);
    }

    @Override
    final protected Object getSearchKey(String uuid) {
        return uuid;
    }
}
