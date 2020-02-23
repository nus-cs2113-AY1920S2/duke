package storage;

import java.nio.file.InvalidPathException;

/**
 * Represents an exception thrown when the storage file path is invalid.
 * 
 */
public class InvalidStorageFilePathException extends InvalidPathException {

    /** 
     * Constructor for an InvalidStorageFilePathException.
     * 
     * @param input The string representation of the file path.
     * @param reason The message of the error.
     */
    public InvalidStorageFilePathException(String input, String reason) {
        super(input, reason);
    }
    
}
