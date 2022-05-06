package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
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
    protected Resume getResume(Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void updateResume(Resume r, Integer searchKey) {
        storage.set(searchKey, r);
    }

    @Override
    protected void addResume(Resume r, Integer searchKey) {
        storage.add(r);
    }

    @Override
    protected void removeResume(Integer searchKey) {
        storage.remove(getResume(searchKey));
    }

    protected Integer getSearchKey(String uuid) {
        return storage.indexOf(new Resume(uuid, "none"));
    }

    @Override
    final protected boolean isExist(Integer searchKey) {
        return (searchKey) < 0;
    }
}
