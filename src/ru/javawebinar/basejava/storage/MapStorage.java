package ru.javawebinar.basejava.storage;

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
    protected Resume getResume(Object index) {
        return storage.get((String) index);
    }

    @Override
    public void updateResume(Resume r, Object index) {
        storage.put((String) index, r);
    }

    @Override
    protected void addResume(Resume r, Object index) {
        storage.put((String) index, r);
    }

    @Override
    protected void removeResume(Object index) {
        storage.remove((String) index);
    }

    @Override
    final protected boolean checkExistCondition(Object index) {
        return !storage.containsKey((String) index);
    }

    @Override
    final protected Object getIndex(String uuid) {
        return uuid;
    }
}
