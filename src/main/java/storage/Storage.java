package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.task.TaskList;
import storage.InvalidStorageFilePathException;

public class Storage {
    
    public static final String DEFAULT_STORAGE_FILEPATH = "storage.txt";
    
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
        File f = new File("C:\\Users\\limwe\\OneDrive\\Documents\\GitHub\\duke\\src\\main\\java\\data\\storage.txt");        
        BufferedReader br = new BufferedReader(new FileReader(f));
        List<String> lines = new ArrayList<>();
        
        try {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new InvalidStorageFilePathException("asdsa", path.toString());
        } 

        return TaskListDecoder.decodeTaskList(lines);

    }
     
    public void save(String filePath, TaskList taskList) throws IOException {
        FileWriter fw;
        try {
            fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            String encodedTaskList = TaskListEncoder.encodeTaskList(taskList); 
            bw.write(encodedTaskList);
            bw.close();
        } catch (IOException e) {
            System.err.println("Something went wrong:" + e.getMessage());
        }
        
    }
    
    public boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }   
}
