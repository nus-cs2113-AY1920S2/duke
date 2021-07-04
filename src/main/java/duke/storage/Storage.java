package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

/**
 * Represents the location of text file where tasks are saved/loaded to.
 * This class ensures that tasks are initially loaded and tasks are saved after any change. 
 */

public class Storage {


    public static  String filePath;
    public static String fileDir;
    public static File file;
    private static TaskList taskList;

    /**
     * The constructor for the Storage class
     * @param taskList pointer to taskList for save/load functions
     */

    public Storage(TaskList taskList){
        this.filePath = "./data/duke.txt";
        this.fileDir = "./data";
        this.taskList = taskList;
    }

    /**
     * Helper function that turns all the tasks in taskList into String format
     * @return string representation of all tasks
     */
    public static String tasksToString(){
        StringBuilder taskString = new StringBuilder();
        for(int i = 0; i < taskList.size(); i++) {
            StringBuilder append = taskString.append(taskList.tasks.get(i)).append("\n");
        }
        return taskString.toString();
    }


    /**
     * Function that saves all tasks onto text file
     */
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

    /**
     * Returns an ArrayList of tasks that is taken from the text file
     * @return an ArrayList type Task
     * @throws DukeException if unable to load contents from textfile
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while(s.hasNext()){
                String task = s.nextLine();
                String taskType = task.substring(1,2);
                String markAsDone = task.substring(4,5);
                String description = task.substring(7,task.length());
                if(taskType.equals("T")){
                    Task todo = new Todo(description);
                    if(!markAsDone.equals("\u2718")) {
                        todo.markAsDone();
                    }
                    loadedTasks.add(todo);
                } else if (taskType.equals("E")){
                   String newDes = description.replace("(", "").replace(")", "");
                   String des = newDes.split(" at: ")[0];
                   String at = newDes.split(" at: ")[1];
                   Task event = new Event(des, at);
                    if(!markAsDone.equals("\u2718")) {
                        event.markAsDone();
                    }
                    loadedTasks.add(event);
                } else if (taskType.equals("D")){
                    String newDes = description.replace("(", "").replace(")", "");
                    String des = newDes.split(" by: ")[0];
                    String by = newDes.split(" by: ")[1];
                    Task deadline = new Event(des, by);
                    if(!markAsDone.equals("\u2718")) {
                        deadline.markAsDone();
                    }
                    loadedTasks.add(deadline);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("There was an error loading tasks from file!");
            throw new DukeException();
        }
        return loadedTasks;
    }
}


