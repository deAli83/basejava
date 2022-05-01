package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1, "name1"));
        storage.save(new Resume(UUID_2, "name2"));
        storage.save(new Resume(UUID_3, "name3"));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAllSorted() {
        Resume[] testStorage = {new Resume(UUID_1, "name1"), new Resume(UUID_2, "name3"), new Resume(UUID_3, "name3")};
        List<Resume> testList = Arrays.asList(testStorage);
        Assert.assertEquals(testList, storage.getAllSorted());
    }

    @Test
    public void get() {
        Resume testResume = storage.get(UUID_1);
        Assert.assertEquals(UUID_1, testResume.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume testResume = storage.get(UUID_3);
        storage.update(testResume);
        Assert.assertSame(testResume, storage.get(UUID_3));
    }

    @Test
    public void save() {
        storage.save(new Resume("uuid4", "name4"));
        Resume testResume = new Resume("uuid4", "name4");
        Assert.assertEquals(testResume, storage.get("uuid4"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_1, "name1"));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }
}