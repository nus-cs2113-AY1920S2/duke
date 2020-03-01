package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import duke.task.TaskList;


public class Storage {


    public static  String filePath;
    public static String fileDir;
    public static File file;
    private static TaskList taskList;


    public Storage(TaskList taskList){
        this.filePath = "./data/duke.txt";
        this.fileDir = "./data";
        this.taskList = taskList;
    }

    // Helper function that turns content of Task into String
    public static String tasksToString(){
        StringBuilder taskString = new StringBuilder();
        for(int i = 0; i < taskList.size(); i++) {
            StringBuilder append = taskString.append(taskList.tasks.get(i)).append("\n");
        }
        return taskString.toString();
    }

    // Function that saves tasks to files
    public void save(){
        File file = new File(fileDir);
        // create a directory if not made
        if(!file.exists()){
            System.out.println("The 'data' directory has not been created. Creating one now!");
            file.mkdir();
        }
        try {
            String filepath = "./data/duke.txt";
            FileWriter fw = new FileWriter(filepath);
            String textToAdd = tasksToString();
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e){
            System.out.println("There was an error trying to save file. Check if file exists!");
        }

    }

    // Function that loads tasks from file
    public void load() {
    // finish load function
    }
}


