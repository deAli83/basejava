package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    final public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    final public void update(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        updateResume(r, index);
    }

    final public void save(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        addResume(r, index);
    }

    final public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        removeResume(uuid, index);
    }

    public abstract int size();

    public abstract void clear();

    public abstract Resume[] getAll();

    protected abstract int getIndex(String uuid);

    protected abstract Resume getResume(int index);

    protected abstract void updateResume(Resume r, int index);

    protected abstract void addResume(Resume r, int index);

    protected abstract void removeResume(String uuid, int index);

}


