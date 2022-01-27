package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, size);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    final public void update(Resume r) {
        String uuid = r.getUuid();
        int index = getIndex(uuid);
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("Resume " + uuid + " not exist");
        }
    }

    final public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            size++;
            add(r, index);
        }
    }

    final public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            size--;
            deleteResume(index);
            storage[size] = null;
        } else {
            System.out.println("Resume " + uuid + " not exist");
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void add(Resume r, int index);

    protected abstract void deleteResume(int index);
}
