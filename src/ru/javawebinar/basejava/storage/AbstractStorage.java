package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    final public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return getResume(findExistedSearchKey(uuid));
    }

    final public void update(Resume r) {
        LOG.info("Update " + r);
        updateResume(r, findExistedSearchKey(r.getUuid()));
    }

    final public void save(Resume r) {
        LOG.info("Save " + r);
        addResume(r, findNotExistedSearchKey(r.getUuid()));
    }

    final public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        removeResume(findExistedSearchKey(uuid));
    }

    private SK findExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK findNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> resumeList = getAll();
        resumeList.sort(Comparator.comparing(Resume::getUuid).thenComparing(Resume::getFullName));
        return resumeList;
    }

    protected abstract boolean isExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract Resume getResume(SK searchKey);

    protected abstract void updateResume(Resume r, SK searchKey);

    protected abstract void addResume(Resume r, SK searchKey);

    protected abstract void removeResume(SK searchKey);

    protected abstract List<Resume> getAll();

}


