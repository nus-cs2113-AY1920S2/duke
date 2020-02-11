package storage;

import java.io.IOException;

public class StorageReadWriteException extends IOException {
    public StorageReadWriteException(String msg) {
        super(msg);
    }
}
