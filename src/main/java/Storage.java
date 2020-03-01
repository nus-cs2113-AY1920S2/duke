package src.main.java;

import src.main.java.duke.exceptions.UnknownLineFromSavedFileException;
import src.main.java.duke.task.Deadline;
import src.main.java.duke.task.Event;
import src.main.java.duke.task.Task;
import src.main.java.duke.task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /*Read in and split up all lines in saved text file*/
    public ArrayList<Task> load() throws UnknownLineFromSavedFileException, FileNotFoundException {
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String[] savedTaskDetails = s.nextLine().split(" \\| ");
            tasks.add(importSavedTasks(savedTaskDetails));
        }
        return tasks;
    }

    /*Identify and create Task based on task type read in saved text file
    * Throws error message if unable to identify task type
    * */
    private static Task importSavedTasks(String[] savedTaskDetails) throws UnknownLineFromSavedFileException {
        switch (savedTaskDetails[0]) {
            case "T":
                Todo todo = new Todo(savedTaskDetails[2]);
                checkTaskStatus(todo, savedTaskDetails[1]);
                return todo;
            case "D":
                Deadline deadline = new Deadline(savedTaskDetails[2], savedTaskDetails[3]);
                checkTaskStatus(deadline, savedTaskDetails[1]);
                return deadline;
            case "E":
                Event event = new Event(savedTaskDetails[2], savedTaskDetails[3]);
                checkTaskStatus(event, savedTaskDetails[1]);
                return event;
            default:
                throw new UnknownLineFromSavedFileException();
        }
    }

    /*Check task status for saved task and
    Update task status for task marked as completed
     */
    private static void checkTaskStatus(Task task, String taskStatus){
        if (taskStatus.equals("1")) {
            task.completedTask();
        }
    }

    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        String fileText = "";
        for (int i = 0; i < Task.getTotalNumberOfTask(); i++) {
            fileText += tasks.getTaskFromList(i).writeInFile();
            fileText += System.lineSeparator();
        }
        fw.write(fileText);
        fw.close();
    }
}
