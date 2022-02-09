package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    ArrayList<Resume> storage = new ArrayList<>();

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
    protected Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    public void updateResume(Resume r, int index) {
        storage.set(index, r);
    }

    @Override
    protected void addResume(Resume r, int index) {
        storage.add(r);
    }

    @Override
    protected void removeResume(String uuid, int index) {
        storage.remove(index);
    }

    @Override
    protected int getIndex(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid() == uuid) {
                return storage.indexOf(r);
            }
        }
        return -1;
    }
}
