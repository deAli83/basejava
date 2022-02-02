package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
        Assert.assertEquals(null, storage.get(UUID_1));
    }

    @Test
    public void getAll() {
        Resume[] testStorage = storage.getAll();
        Assert.assertEquals(storage.get(UUID_1), testStorage[0]);
        Assert.assertEquals(storage.get(UUID_2), testStorage[1]);
        Assert.assertEquals(storage.get(UUID_3), testStorage[2]);
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Resume testResume = storage.get(UUID_1);
        Assert.assertEquals(UUID_1, testResume.getUuid());
    }

    @Test
    public void update() {
        storage.update(storage.get(UUID_3));
        Resume testResume = storage.get(UUID_3);
        Assert.assertEquals(UUID_3, testResume.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void save() {
        String UUID_4 = "uuid4";
        storage.save(new Resume(UUID_4));
        Resume testResume = storage.get(UUID_4);
        Assert.assertEquals(UUID_4, testResume.getUuid());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT - 1; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }
}