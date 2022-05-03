package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    final public Resume get(String uuid) {
        return getResume(findExistedSearchKey(uuid));
    }

    final public void update(Resume r) {
        updateResume(r, findExistedSearchKey(r.getUuid()));
    }

    final public void save(Resume r) {
        addResume(r, findNotExistedSearchKey(r.getUuid()));
    }

    final public void delete(String uuid) {
        removeResume(findExistedSearchKey(uuid));
    }

    final private Object findExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    final private Object findNotExistedSearchKey(String uuid) {
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


