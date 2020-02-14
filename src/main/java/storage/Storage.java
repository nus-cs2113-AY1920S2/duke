package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.task.TaskList;
import storage.InvalidStorageFilePathException;

import static misc.Messages.MESSAGE_READ_WRITE_FAILURE;

public class Storage {
    
    public static final String DEFAULT_STORAGE_FILEPATH = "data/storage.txt";
    
    public final Path path;
    
    public Storage() {
        this(DEFAULT_STORAGE_FILEPATH);
    }
    
    
    public Storage(String filePath) {
        this.path = Paths.get(filePath);
        
        if (!isValidPath(this.path)) {
            throw new InvalidStorageFilePathException(this.path.toString(),
                     "The current file does not end with a .txt");
        }
    }
    
    public TaskList load() throws IOException {
        List<String> lines = new ArrayList<>();
        
        try {
            String line;
            File f = new File(this.path.toString());        
            BufferedReader br = new BufferedReader(new FileReader(f));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new StorageReadWriteException(MESSAGE_READ_WRITE_FAILURE);
        }
        return TaskListDecoder.decodeTaskList(lines);
    }
     
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
    
    public boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }   
}
