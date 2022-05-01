package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    final public Resume get(String uuid) {
        return getResume(checkExistResume(uuid));
    }

    final public void update(Resume r) {
        updateResume(r, checkExistResume(r.getUuid()));
    }

    final public void save(Resume r) {
        addResume(r, checkNoExistResume(r.getUuid()));
    }

    final public void delete(String uuid) {
        removeResume(checkExistResume(uuid));
    }

    final protected Object checkExistResume(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    final protected Object checkNoExistResume(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumeList = getAll();
        resumeList.sort(Comparator.comparing(Resume::getUuid).thenComparing(Resume::getFullName));
        return resumeList;
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract Resume getResume(Object searchKey);

    protected abstract void updateResume(Resume r, Object searchKey);

    protected abstract void addResume(Resume r, Object searchKey);

    protected abstract void removeResume(Object searchKey);

    protected abstract List<Resume> getAll();

}


