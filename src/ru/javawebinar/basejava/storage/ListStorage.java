package ru.javawebinar.basejava.storage;

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
    public List<Resume> getAll() {
        return storage;
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected void updateResume(Resume r, Object searchKey) {
        storage.set((Integer) searchKey, r);
    }

    @Override
    protected void addResume(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    protected void removeResume(Object searchKey) {
        storage.remove(getResume(searchKey));
    }

    protected Object getSearchKey(String uuid) {
        return storage.indexOf(new Resume(uuid, "none"));
    }

    @Override
    final protected boolean isExist(Object searchKey) {
        return ((Integer) searchKey) < 0;
    }
}
