package storage;

import static misc.Messages.MESSAGE_READ_WRITE_FAILURE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.task.TaskList;
import storage.InvalidStorageFilePathException;

/**
 * Represents the storage that stores all information pertaining
 * to tasks in a storage.txt file.
 */
public class Storage {
    
    /** The default file name that is to be read or write by the program. */
    public static final String DEFAULT_STORAGE_FILEPATH = "storage.txt";
    
    public final Path path;
    
    /** Constructor of a Storage using the default file path. */
    public Storage() {
        this(DEFAULT_STORAGE_FILEPATH);
    }
    
    /** 
     * Constructor of a Storage retrieved from Path.
     * Throws an exception when there is no file that matches "storage.txt".
     * 
     * @param filePath
     * @throws InvalidStorageFilePathException
     */
    public Storage(String filePath) {
        this.path = Paths.get(filePath);
        
        if (!isValidPath(this.path)) {
            throw new InvalidStorageFilePathException(this.path.toString(),
                     "The current file does not end with a .txt");
        }
    }
    
    /** 
     * Loads all data from the storage file.
     * 
     * @return
     * @throws IOException
     */
    public TaskList load() throws IOException {
        List<String> lines = new ArrayList<>();
        
        // Find an existing storage.txt file first, else create a new storage.txt.
        File f = new File(this.path.toString()); 
        f.createNewFile();
        FileOutputStream outputFile = new FileOutputStream(f, false);
        outputFile.close();
        
        try {
            String line;                       
            BufferedReader br = new BufferedReader(new FileReader(this.path.toString()));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new StorageReadWriteException(MESSAGE_READ_WRITE_FAILURE);
        }
        return TaskListDecoder.decodeTaskList(lines);
    }
    
    /**
     * Write data into the storage file.
     * Throws an exception if the file cannot be read or written.
     * 
     * @param filePath
     * @param taskList
     * @throws IOException
     * @throws StorageReadWriteException
     */
    public void save(String filePath, TaskList taskList) throws IOException {      
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            String encodedTaskList = TaskListEncoder.encodeTaskList(taskList); 
            bw.write(encodedTaskList);
            bw.close();
        } catch (IOException e) {
            throw new StorageReadWriteException(MESSAGE_READ_WRITE_FAILURE);
        }        
    }
    
    public String getFilePath() {
        return this.path.toString();
    }
    
    /** Test if a given file has a .txt extension. */
    public boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }   
}
