package storage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.Messages;
import data.Duke;
import data.exceptions.ParseException;
import data.exceptions.StorageOperationException;
import parser.EncodeToFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.io.File.pathSeparator;

/**
 * Represents the file used to store task list data.
 */
public class StorageFile {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "duke.txt";
    public static final String DEFAULT_STORAGE_JSON_FILEPATH = "duke.json";
    private static String jsonFilePath = DEFAULT_STORAGE_JSON_FILEPATH;
    private Path filePath;

    /**
     * default constructor (if the user does not specified file location
     */
    public StorageFile() throws StorageOperationException {
        this(DEFAULT_STORAGE_FILEPATH);
        this.filePath = Paths.get(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Parses user input into command for execution.
     *
     * @param filePath
     * @throws StorageOperationException
     * @retuen parsed storage file object
     */
    public StorageFile(String filePath) throws StorageOperationException {
        this.filePath = Paths.get(filePath);
        if (!isValidPath(this.filePath)) {
            throw new StorageOperationException("Storage file should end with '.txt'");
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param duke duke management system
     * @throws StorageOperationException
     */
    public void save(Duke duke) throws StorageOperationException {
        try {
            List<String> encodedDuke = EncodeToFile.encodeAllTasks(duke);
            Files.write(filePath, encodedDuke);
        } catch (IOException ioe) {
            throw new StorageOperationException(
                    String.format(Messages.MESSAGE_FILE_OPERATION_IO_ERROR, filePath));
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param filePath
     * @return (boolean) if the filePath is valid, return true
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }


    public static void saveJson(Duke duke) throws StorageOperationException {
        //System.out.printf(FIVE_SPACES + SAVE_DATA_TO_FILE_PROMPT_FORMAT_STRING, DATA_FILE_PATH);
        try {
            Gson gson = new GsonBuilder().create();
            FileWriter fw = new FileWriter(String.valueOf(jsonFilePath));
            String json = gson.toJson(duke.getTaskList().getInternalList());
            fw.write(json);
            fw.flush();
            fw.close();
            //System.out.println(FIVE_SPACES + DATA_SAVED_SUCCESSFULLY_PROMPT);
        } catch (IOException e) {
            throw new StorageOperationException(
                    String.format(Messages.MESSAGE_FILE_OPERATION_IO_ERROR, jsonFilePath));
        }
    }
}
