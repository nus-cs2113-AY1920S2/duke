package storage;

import java.io.IOException;

/**
 * Represents an exception that is thrown whenever read or write
 * operation fails.
 * 
 */
public class StorageReadWriteException extends IOException {
    
    public StorageReadWriteException(String msg) {
        super(msg);
    }
}
