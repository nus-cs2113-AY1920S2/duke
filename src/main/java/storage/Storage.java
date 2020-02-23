package storage;

import static misc.Messages.MESSAGE_READ_WRITE_FAILURE;

import java.io.BufferedOutputStream;
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
 * 
 */
public class Storage {
    
    /** The default file name that is to be read or written by the program. */
    public static final String DEFAULT_STORAGE_FILEPATH = "storage.txt";
    
    public final Path path;
    
    /** Constructor of a Storage using the default file path. */
    public Storage() {
        this(DEFAULT_STORAGE_FILEPATH);
    }
    
    /** 
     * Constructor of a Storage retrieved from Path.
     * 
     * @param filePath
     * @throws InvalidStorageFilePathException  If the storage file does not match
     *                                          a .txt extension.
     */
    public Storage(String filePath) {
        this.path = Paths.get(filePath);
        
        if (!isValidPath(this.path)) {
            throw new InvalidStorageFilePathException(this.path.toString(),
                     "The current file does not end with a .txt");
        }
    }
    
    /** 
     * Silently loads all data from the storage text file. This method first
     * tries to read from a storage.txt file. If this file is not present in
     * the same directory as the program, it will create a new file that is
     * named after 'storage.txt' as well.
     * 
     * @return A TaskList with all the data of tasks stored in it.
     * @throws StorageReadWriteException  If the current storage file cannot be
     *                                    loaded.  
     */
    public TaskList load() throws StorageReadWriteException {
        List<String> lines = new ArrayList<>();        
        
        File f = new File(this.path.toString());        
        try {
            f.createNewFile(); 
            
            FileOutputStream outputFile = new FileOutputStream(f, true);
            BufferedOutputStream bufferedOutputFile = 
                    new BufferedOutputStream(outputFile, 1024);
            
            bufferedOutputFile.close();
        } catch (IOException e) {
            throw new StorageReadWriteException(
                    MESSAGE_READ_WRITE_FAILURE);
        }
                
        try {
            String line;                       
            BufferedReader br = new BufferedReader(
                    new FileReader(this.path.toString()));
            
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new StorageReadWriteException(
                    MESSAGE_READ_WRITE_FAILURE);
        }
        return StorageDecoder.decodeStorageFile(lines);
    }
    
    /**
     * Writes data into the storage text file. Throws an exception if the file 
     * cannot be read or written.
     * 
     * @throws StorageReadWriteException  If the current storage file cannot be
     *                                    overwritten.  
     */
    public void save(String filePath, TaskList taskList) 
            throws StorageReadWriteException {
        
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            String encodedTaskList = StorageEncoder.
                    encodeTaskList(taskList); 
            
            bw.write(encodedTaskList);
            bw.close();
        } catch (IOException e) {
            throw new StorageReadWriteException(
                    MESSAGE_READ_WRITE_FAILURE);
        }        
    }
    
    public String getFilePath() {
        return this.path.toString();
    }
    
    /** Returns true if a given file has a .txt extension. */
    public boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }   
}
