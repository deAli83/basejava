package ru.javawebinar.basejava.storage;

import java.io.IOException;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() throws IOException {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStream()));
    }
}