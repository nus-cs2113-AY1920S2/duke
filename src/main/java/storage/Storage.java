package storage;

import tasktype.Deadline;
import tasktype.Event;
import tasktype.Task;
import tasktype.Todo;
import tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static common.Messages.TASKLIST_SAVE_DIRECTORY;
import static common.Messages.TASKLIST_SAVE_FILEPATH;
import static common.Messages.TASKLIST_SAVE_PIPE_DELIMITER;


/**
 * This class is used to load and save the data of Tasks in the {@link TaskList} object to a local .txt file.
 */
public class Storage {


    private String filePath;
    private static final String TASK_NOTATION = "T";
    private static final String EVENT_NOTATION = "E";
    private static final String DEADLINE_NOTATION = "D";
    protected final String TASK_DONE_NOTATION = "1";
    private static final String PIPE_NOTATION = " | ";

    public Storage(String filePathInput) {
        this.filePath = filePathInput;
    }

    /**
     * This method loads the saved data from the local save file before returning a {@link TaskList}.
     * <p></p>
     * <p>If there is no saved data present, this will throw a {@link FileNotFoundException} which
     * creates an empty TaskList. Else, it will load the saved tasks to a TaskList </p>
     * @return an ArrayList<Task> containing the tasks
     * @throws FileNotFoundException this exception occurs if no local save file is found. Handled by the data.Duke class which creates a new empty TaskList<Task>
     */
    public ArrayList<Task> loadFileToTaskList() throws FileNotFoundException {
        File f = new File(this.filePath);
        if (!f.exists()) {
            File newDirectory = new File(TASKLIST_SAVE_DIRECTORY);
            boolean isNewDirectoryCreated = newDirectory.mkdir();
            if (isNewDirectoryCreated) {
                File newFile = new File(TASKLIST_SAVE_FILEPATH);
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
            String[] tokenizedTaskString = taskString.split(TASKLIST_SAVE_PIPE_DELIMITER);
            Task newTaskToLoad;
            switch(tokenizedTaskString[0].toUpperCase()) {
            case (TASK_NOTATION):
                newTaskToLoad = new Todo(tokenizedTaskString[2]);
                break;
            case (EVENT_NOTATION):
                newTaskToLoad = new Event(tokenizedTaskString[2], tokenizedTaskString[3]);
                break;
            case (DEADLINE_NOTATION):
                newTaskToLoad = new Deadline(tokenizedTaskString[2], tokenizedTaskString[3]);
                break;
            default:
                return null;
            }
            //if task was previously marked done already, make sure to mark it as done when loading to taskList
            if (tokenizedTaskString[1].equals(TASK_DONE_NOTATION)) {
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
                newTaskString = newTaskData.getTaskData()[0] + PIPE_NOTATION
                        + newTaskData.getTaskData()[1] + PIPE_NOTATION
                        + newTaskData.getTaskData()[2] + System.lineSeparator();
            } else {
                newTaskString = newTaskData.getTaskData()[0] + PIPE_NOTATION
                        + newTaskData.getTaskData()[1] + PIPE_NOTATION
                        + newTaskData.getTaskData()[2] + PIPE_NOTATION
                        + newTaskData.getTaskData()[3] + System.lineSeparator();
            }
            fw.write(newTaskString);

        }
        fw.close();

    }
}
