package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the task of loading and saving data as required.
 */
public class Storage {

    /** The location of the data file */
    private String filePath;

    /**
     * Constructor for Storage Class.
     * It assigns the filepath for the data file as provided by the user.
     *
     * @param filePath Location of the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the data previously stored in the data file by calling {@link #loadTasks()}.
     * Also handles IOException that might be thrown during the execution of {@link #loadTasks()},
     * in which case it returns an empty list of tasks.
     *
     * @return taskList Contains all the tasks stored in the previous execution of the application.
     * @see #loadTasks()
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> taskList;
        try {
            taskList = loadTasks();
        } catch (IOException e) {
            e.printStackTrace();
            taskList = new ArrayList<>();
        }
        return taskList;
    }

    /**
     * Returns the data previously stored in the data file by calling {@link #loadTasksFromFile()}.
     * Also handles FileNotFoundException that might be thrown during the execution of {@link #loadTasksFromFile()},
     * in which case it creates a new data(.txt) file at the predefined location and returns an empty list of tasks.
     * It also throws an IOException.
     *
     * @return taskList Contains all the tasks stored in the previous execution of the application.
     * @throws IOException If issues were encountered during loading of data.
     * @see #loadTasksFromFile()
     */
    private ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> taskList;
        try {
            taskList = loadTasksFromFile();
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            file.createNewFile();
            taskList = new ArrayList<>();
        }
        return  taskList;
    }

    /**
     * Returns the data previously stored in the data file as a TaskList Object.
     *
     * @return tasks Contains all the tasks stored in the previous execution of the application.
     * @throws FileNotFoundException if the data file isn't present at the target location.
     */
    private ArrayList<Task> loadTasksFromFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String taskDescription = s.nextLine();
            String[] splitDescription = taskDescription.split("#",3);
            char taskType = splitDescription[0].charAt(0);
            boolean isDone = Boolean.parseBoolean(splitDescription[1].trim());
            String description = splitDescription[2].trim();

            switch (taskType) {
            case 'T':
                ToDo toDo = new ToDo(description);
                toDo.setDone(isDone);
                tasks.add(toDo);
                break;
            case 'D':
                Deadline deadline = new Deadline(description);
                deadline.setDone(isDone);
                tasks.add(deadline);
                break;
            case 'E':
                Event event = new Event(description);
                event.setDone(isDone);
                tasks.add(event);
                break;
            default:
                Ui.printWithIndentation("Error encountered during Execution");
                break;
            }
        }
        return tasks;
    }

    /**
     * Saves the tasks provided by the user as a TaskList Object to the data (.txt) file.
     *
     * @param taskList Contains all the tasks provided by the user to be saved later.
     */
    public void storeTasksToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList.tasks) {
                switch (task.taskType) {
                case 'T':
                    ToDo toDo = (ToDo) task;
                    fw.write("T # " + toDo.isDone + " # " + toDo.description + System.lineSeparator());
                    break;
                case 'D':
                    Deadline deadline = (Deadline) task;
                    fw.write("D # " + deadline.isDone + " # " + deadline.description + "/by "
                            + deadline.getDeadlineInInputFormat() + System.lineSeparator());
                    break;
                case 'E':
                    Event event = (Event) task;
                    fw.write("E # " + event.isDone + " # " + event.description + "/at "
                            + event.getPeriodWithoutBraces() + System.lineSeparator());
                    break;
                default:
                    // Add Exception handling
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
