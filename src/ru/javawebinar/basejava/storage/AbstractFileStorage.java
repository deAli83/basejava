package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public boolean isExist(File file) {
        return !file.exists();
    }

    @Override
    public File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public Resume getResume(File file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    public void updateResume(Resume r, File file) {
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    public void addResume(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
    }



    @Override
    public void removeResume(File file) {
        file.delete();
    }

    @Override
    public List<Resume> getAll() {
        File[] files = directory.listFiles();
        List<Resume> resumes = new ArrayList<>();
        for (File file : files) {
            try {
                resumes.add(doRead(new BufferedInputStream(new FileInputStream(file))));
            } catch (IOException e) {
                throw new StorageException("IO error", file.getName(), e);
            }
        }
        return resumes;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        return list.length;
    }

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;
}
