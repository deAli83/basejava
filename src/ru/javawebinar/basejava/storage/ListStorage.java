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
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    protected void updateResume(Resume r, Object index) {
        storage.set((Integer) index, r);
    }

    @Override
    protected void addResume(Resume r, Object index) {
        storage.add(r);
    }

    @Override
    protected void removeResume(Object index) {
        storage.remove(getResume(index));
    }

    protected Object getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    final protected boolean checkExistCondition(Object index) {
        return ((Integer) index) < 0;
    }
}
