package storage;

import java.nio.file.InvalidPathException;

public class InvalidStorageFilePathException extends InvalidPathException {

    public InvalidStorageFilePathException(String input, String reason) {
        super(input, reason);
    }
    
}
