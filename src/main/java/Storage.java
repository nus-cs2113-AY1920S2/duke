import data.Deadline;
import data.Event;
import data.Task;
import data.Todo;
import tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to load and save the data of Tasks in the {@link TaskList} object to a local .txt file.
 */
public class Storage {

    private String filePath;

    public Storage(String filePathInput) {
        this.filePath = filePathInput;
    }

    /**
     * This method loads the saved data from the local save file before returning a {@link TaskList}.
     * <p></p>
     * <p>If there is no saved data present, this will throw a {@link FileNotFoundException} which
     * creates an empty TaskList. Else, it will load the saved tasks to a TaskList </p>
     * @return an ArrayList<Task> containing the tasks. If there is no existing save file, an empty ArrayList is returned instead.
     * @throws FileNotFoundException this exception occurs if no local save file is found. Handled by the Duke class which creates a new empty ArrayList<Task>
     */
    public ArrayList<Task> loadFileToTaskList() throws FileNotFoundException {
        File f = new File(this.filePath);
        if (!f.exists()) {
            File newDirectory = new File("data");
            boolean isNewDirectoryCreated = newDirectory.mkdir();
            if (isNewDirectoryCreated) {
                File newFile = new File("data/duke.txt");
                try {
                    newFile.createNewFile();
                } catch (IOException ex) {
                    System.out.println("Failed to create file in new directory");
                }

            } else {
                System.out.println("Failed to create directory");
            }
            throw new FileNotFoundException();
        }
        ArrayList<Task> taskListToReturn = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            //add task (each line) to ArrayList taskList
            //1. process each line first, construct new Todo/Event/Deadline object
            String taskString = s.nextLine();
            String[] tokenizedTaskString = taskString.split(" \\| ");
            Task newTaskToLoad;
            switch(tokenizedTaskString[0].toUpperCase()) {
            case ("T"):
                newTaskToLoad = new Todo(tokenizedTaskString[2], "");
                break;
            case ("E"):
                newTaskToLoad = new Event(tokenizedTaskString[2], tokenizedTaskString[3]);
                break;
            case ("D"):
                newTaskToLoad = new Deadline(tokenizedTaskString[2], tokenizedTaskString[3]);
                break;
            default:
                //print invalid task loaded error message
                return null;
            }
            //if task was previously marked done already, make sure to mark it as done when loading to taskList
            if (tokenizedTaskString[1].equals("1")) {
                newTaskToLoad.markAsDone();
            }
            taskListToReturn.add(newTaskToLoad);

        }

        return taskListToReturn;
    }

    /**
     * This method saves the data stored in the Tasks of the {@link TaskList} object input into the local save file itself.
     * @param taskList      the <code>ArrayList<Task></code> of tasks
     * @throws IOException  this exception occurs if the Task data was unable to be written to the local save file.
     */
    public void saveTaskListToFile(TaskList taskList) throws IOException {
        FileWriter fw;
        try {
            fw = new FileWriter(this.filePath);
        } catch (IOException e) {
            throw new IOException();
        }
        //convert newTaskData to string format for storing in duke.txt
        String newTaskString;

        for (int i=0; i < taskList.getTaskCount(); i++) {
            Task newTaskData = taskList.getTaskList().get(i);
            if (newTaskData instanceof Todo) {
                newTaskString = newTaskData.getTaskData()[0] + " | " + newTaskData.getTaskData()[1] + " | "
                        + newTaskData.getTaskData()[2] + System.lineSeparator();
            } else {
                newTaskString = newTaskData.getTaskData()[0] + " | " + newTaskData.getTaskData()[1] + " | "
                        + newTaskData.getTaskData()[2] + " | " + newTaskData.getTaskData()[3] + System.lineSeparator();
            }
            fw.write(newTaskString);

        }
        fw.close();

    }
}
