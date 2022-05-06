package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {
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
    public List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void updateResume(Resume r, String uuid) {
        storage.put(uuid, r);
    }

    @Override
    protected void addResume(Resume r, String uuid) {
        storage.put(uuid, r);
    }

    @Override
    protected void removeResume(String uuid) {
        storage.remove(uuid);
    }

    @Override
    final protected boolean isExist(String uuid) {
        return !storage.containsKey(uuid);
    }

    @Override
    final protected String getSearchKey(String uuid) {
        return uuid;
    }
}
