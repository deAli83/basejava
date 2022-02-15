package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    final public Resume get(String uuid) {
        Object index = getIndex(uuid);
        checkExistIndex(index, uuid);
        return getResume(index);
    }

    final public void update(Resume r) {
        String uuid = r.getUuid();
        Object index = getIndex(r.getUuid());
        checkExistIndex(index, uuid);
        updateResume(r, index);
    }

    final public void save(Resume r) {
        String uuid = r.getUuid();
        Object index = getIndex(r.getUuid());
        checkNoExistIndex(index, uuid);
        addResume(r, index);
    }

    final public void delete(String uuid) {
        Object index = getIndex(uuid);
        checkExistIndex(index, uuid);
        removeResume(index);
    }

    final protected void checkExistIndex(Object index, String uuid) {
        if (checkExistCondition(index)) {
            throw new NotExistStorageException(uuid);
        }
    }

    final protected void checkNoExistIndex(Object index, String uuid) {
        if (!checkExistCondition(index)) {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract boolean checkExistCondition(Object index);

    protected abstract Object getIndex(String uuid);

    protected abstract Resume getResume(Object index);

    protected abstract void updateResume(Resume r, Object index);

    protected abstract void addResume(Resume r, Object index);

    protected abstract void removeResume(Object index);

}


