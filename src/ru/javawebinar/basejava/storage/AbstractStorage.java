package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    final public Resume get(String uuid) {
        checkExistIndex(uuid);
        return getResume(uuid);
    }

    final public void update(Resume r) {
        checkExistIndex(r.getUuid());
        updateResume(r);
    }

    final public void save(Resume r) {
        checkNoExistIndex(r.getUuid());
        addResume(r);
    }

    final public void delete(String uuid) {
        checkExistIndex(uuid);
        removeResume(uuid);
    }

    protected abstract void checkExistIndex(String uuid);

    protected abstract void checkNoExistIndex(String uuid);

    protected abstract Resume getResume(String uuid);

    protected abstract void updateResume(Resume r);

    protected abstract void addResume(Resume r);

    protected abstract void removeResume(String uuid);

}


