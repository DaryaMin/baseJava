package com.uirise.webapp.exception;

public class StorageException extends RuntimeException {
    private String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message, e);
        this.uuid = uuid;
    }

    public StorageException(Exception e) {
        this(e.getMessage());
    }

    public StorageException(String message) {
        super(message);
    }

    public String getUuid() {
        return uuid;
    }
}