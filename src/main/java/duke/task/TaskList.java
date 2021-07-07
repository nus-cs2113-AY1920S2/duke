package duke.task;

import duke.storage.Storage;
import duke.storage.StorageHandler;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class containing a list of Tasks.
 */
public class TaskList {

    /** List containing Task objects. */
    private List<Task> taskList;

    /**
     * Constructs new TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Loads taskList from data file.
     *
     * @param dataFile Data file to load from.
     */
    public TaskList(File dataFile) {
        taskList = new ArrayList<Task>();
        populateTaskList(dataFile);
    }

    /**
     * Populates task list from file.
     *
     * @param dataFile Data file to populate from.
     */
    private void populateTaskList(File dataFile) {
        try {
            Scanner dataScanner = new Scanner(dataFile);
            while (dataScanner.hasNext()) {
                parseDataLine(dataScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            Ui.formatPrint("Error: data file not found. Could not load into the current session's task list.");
        }
    }

    /**
     * Returns the size of the taskList.
     *
     * @return The size of taskList.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the Task at the input index.
     *
     * @param index Index of desired task.
     * @return Task at desired index. In case index is invalid, it returns an empty task.
     */
    public Task getTask(int index) {
        if (index >= 0 && taskList.size() > index) {
            return taskList.get(index);
        } else {
            return new Task("");
        }
    }

    /**
     * Parses the current line in the data file to a Task object.
     *
     * @param s String to parse.
     */
    private void parseDataLine(String s) {

        List<String> strings = Arrays.asList(s.split(","));

        boolean isDone = Boolean.parseBoolean(strings.get(2));

        parseDataLine_parseTask(strings, isDone);
    }

    /**
     * Parses sections of a string to different components of a Task object; finally, adds Task to list.
     *
     * @param strings Components of initial data line.
     * @param isDone Whether the task is done.
     */
    private void parseDataLine_parseTask(List<String> strings, boolean isDone) {
        switch (strings.get(1)) {
        case "T":
            Todo t = new Todo(isDone, strings.get(3));
            taskList.add(t);
            break;
        case "D":
            Deadline d = new Deadline(isDone, strings.get(3), LocalDate.parse(strings.get(4)));
            taskList.add(d);
            break;
        case "E":
            Event e = new Event(isDone, strings.get(3), LocalDate.parse(strings.get(4)));
            taskList.add(e);
            break;
        default:
            Ui.formatPrint("Error: task type not recognized");
            break;
        }
    }

    /**
     * Adds task to taskList and writes it to the data file.
     *
     * @param t Task to add.
     * @param storage Storage object for data file.
     */
    public void addTask(Task t, Storage storage) {
        taskList.add(t);

        int taskId = taskList.size()-1;

        String dataLine = t.toData(taskId);

        addTask_updateFile(storage, dataLine);
        Ui.formatPrint("Added task: " + t);
    }

    /**
     * Updates data file with new task.
     *
     * @param storage Storage object for data file.
     * @param dataLine Line to write to file.
     */
    private void addTask_updateFile(Storage storage, String dataLine) {
        try {
            storage.writeToFile(dataLine);
        } catch (IOException e) {
            Ui.formatPrint("Error saving task to data file.");
        }
    }

    /**
     * Sets task as done.
     *
     * @param taskId ID of task to set as done.
     * @param storage Storage object for data file.
     */
    public void setDone(int taskId, Storage storage) {
        taskList.get(taskId).markAsDone(); // Mark task with that ID as done
        setDone_updateFile(taskId, storage);
        Ui.formatPrint("Marked task as done.");
    }

    /**
     * Writes that task is done to file.
     *
     * @param taskId ID of task to set as done.
     * @param storage Storage object for data file.
     */
    private void setDone_updateFile(int taskId, Storage storage) {
        try {
            StorageHandler.replaceLine(taskId, taskList.get(taskId).toData(taskId), storage.dataFilePath);
        } catch (IOException e) {
            Ui.formatPrint("Error updating line in data file.");
        }
    }

    /**
     * Deletes task from task list and data file.
     *
     * @param taskId ID of task to remove.
     * @param storage Storage object for data file.
     */
    public void deleteTask(int taskId, Storage storage) {
        taskList.remove(taskId);
        deleteTask_updateFile(taskId, storage);
        Ui.formatPrint("Deleted task " + (taskId+1));
    }

    /**
     * Removes task from data file.
     *
     * @param taskId ID of task to remove.
     * @param storage Storage object for data file.
     */
    private void deleteTask_updateFile(int taskId, Storage storage) {
        try {
            StorageHandler.removeLine(taskId, storage.dataFilePath);
        } catch (IOException e) {
            Ui.formatPrint("Error while deleting task from data file.");
        }
    }

    /**
     * Finds string in descriptions of tasks in the task list.
     *
     * @param toFind String to find.
     */
    public void find(String toFind) {
        String s = toFind.trim();
        List<String> matches = new ArrayList<String>();
        int matchCounter = 0;

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(s)) {
                matches.add(taskList.get(i).toString());
                matchCounter += 1;
            }
        }
        System.out.println("Found " + matchCounter + " match(es) in your list:");
        Ui.formatPrint(matches);
    }
}
