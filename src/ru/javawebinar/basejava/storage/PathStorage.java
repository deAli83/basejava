package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final StreamStrategy strategy;

    protected PathStorage(String dir, StreamStrategy strategy) {
        directory = Paths.get(dir);
        this.strategy = strategy;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        try {
            streamPath().forEach(this::removeResume);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() throws IOException {
        return (int) streamPath().count();
    }

    @Override
    protected boolean isExist(Path path) {
        return !Files.isRegularFile(path);
    }

    @Override
    public Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    public Resume getResume(Path path) {
        try {
            return strategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Access file error", path.getFileName().toString(), e);
        }
    }

    @Override
    public void updateResume(Resume r, Path path) {
        try {
            strategy.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Access file error", path.getFileName().toString(), e);
        }
    }

    @Override
    public void addResume(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file ", path.toString(), e);
        }
        updateResume(r, path);
    }

    @Override
    public void removeResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Delete error ", path.toString(), e);
        }
    }

    @Override
    public List<Resume> getAll() {
        List<Resume> list = new ArrayList<>();
        try {
            streamPath().forEach(path -> {
                list.add(getResume(path));
            });
        } catch (IOException e) {
            throw new StorageException(null, "Directory read error");
        }
        return list;
    }

    private Stream<Path> streamPath() throws IOException {
        return Files.list(directory);
    }
}
