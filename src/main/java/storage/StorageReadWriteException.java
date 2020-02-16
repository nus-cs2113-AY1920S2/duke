package storage;

import java.io.IOException;

/**
 * Represents an exception that is thrown when read or write
 * operations fail.
 */
public class StorageReadWriteException extends IOException {
    
    /** 
     * Constructor for StorageReadWriteException.
     * 
     * @param msg The error message of this exception.
     */
    public StorageReadWriteException(String msg) {
        super(msg);
    }
}
