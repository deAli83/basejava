package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
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
    protected Resume getResume(Object resume) {
        return (Resume) resume;
    }

    @Override
    public void updateResume(Resume r, Object resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void addResume(Resume r, Object resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void removeResume(Object resume) {
        storage.remove(((Resume) resume).getUuid());
    }

    @Override
    final protected boolean isExist(Object resume) {
        return resume == null;
    }

    @Override
    final protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}
