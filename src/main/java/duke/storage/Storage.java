package duke.storage;
import duke.taskList.TaskList;
import duke.exception.IncorrectValueException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Represents the file used to store address book data.
 */

public class Storage {
    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_FILE = "duke.txt";
    public final Path path;


    public Storage() throws StorageFilePathException {
        this(DEFAULT_FILE);
    }

    public Storage(String filePath) throws StorageFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new StorageFilePathException("Storage file should end with '.txt'");
        }
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Saves the {@code addressBook} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(TaskList tasklist) throws StorageOperationException {
        try {
            List<String> writeTaskList = SaveTaskList.saveTaskList(tasklist);
            Files.write(path, writeTaskList);
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    public TaskList load() throws StorageOperationException {

        if (!Files.isRegularFile(path) || !Files.exists(path) ) {
            return new TaskList();
        }

        try {
            return ReadTaskList.accessTaskList(Files.readAllLines(path));
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("Sheena: The non-existent file scenario is already handled.");
            // other errors
        } catch (IOException ioe) {
            throw new StorageOperationException("Sheena: Error writing to file " + path);
        } catch (IncorrectValueException ive) {
            throw new StorageOperationException("Sheena: Hey! File contains illegal data!");
        }
    }

    public String getPath() {
        return path.toString();
    }


    public static class StorageFilePathException extends IncorrectValueException {
        public StorageFilePathException(String message) {
            super(message);
        }
    }

    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

}