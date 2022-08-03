package ru.javawebinar.basejava.exception;

import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class StorageException extends RuntimeException {
    private final String uuid;
    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }
    public StorageException(Exception e) {
        this(e.getMessage(), e);
    }
    public StorageException(String message, Exception e) {
        this(message, null, e);
    }
    public StorageException(String message, String uuid, Exception e) {
        super(message, e);
        this.uuid = uuid;
    }
    public String getUuid() {
        return uuid;
    }

    public static StorageException catchSqlException(SQLException e) {
        if (e instanceof PSQLException) {
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}