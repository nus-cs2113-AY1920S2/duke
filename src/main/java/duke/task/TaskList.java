package duke.task;

import duke.storage.Storage;
import duke.storage.StorageHandler;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public TaskList(File dataFile) {
        taskList = new ArrayList<Task>();
        populateTaskList(dataFile);
    }

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

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    private void parseDataLine(String s) {

        List<String> strings = Arrays.asList(s.split(","));

        boolean isDone = Boolean.parseBoolean(strings.get(2));

        parseDataLine_parseTask(strings, isDone);
    }

    private void parseDataLine_parseTask(List<String> strings, boolean isDone) {
        switch (strings.get(1)) {
        case "T":
            Todo t = new Todo(isDone, strings.get(3));
            taskList.add(t);
            break;
        case "D":
            Deadline d = new Deadline(isDone, strings.get(3), strings.get(4));
            taskList.add(d);
            break;
        case "E":
            Event e = new Event(isDone, strings.get(3), strings.get(4));
            taskList.add(e);
            break;
        }
    }

    public void addTask(Task t, Storage storage) {
        taskList.add(t);

        int taskId = taskList.size()-1;

        String dataLine = t.toData(taskId);

        addTask_updateFile(storage, dataLine);
        Ui.formatPrint("Added task: " + t);
    }

    private void addTask_updateFile(Storage storage, String dataLine) {
        try {
            storage.writeToFile(dataLine);
        } catch (IOException e) {
            Ui.formatPrint("Error saving task to data file.");
        }
    }

    public void setDone(int taskId, Storage storage) {
        taskList.get(taskId).markAsDone(); // Mark task with that ID as done
        setDone_updateFile(taskId, storage);
        Ui.formatPrint("Marked task as done.");
    }

    private void setDone_updateFile(int taskId, Storage storage) {
        try {
            StorageHandler.replaceLine(taskId, taskList.get(taskId).toData(taskId), storage.dataFilePath);
        } catch (IOException e) {
            Ui.formatPrint("Error updating line in data file.");
        }
    }

    public void deleteTask(int taskId, Storage storage) {
        taskList.remove(taskId);
        deleteTask_updateFile(taskId, storage);
        Ui.formatPrint("Deleted task " + (taskId+1));
    }

    private void deleteTask_updateFile(int taskId, Storage storage) {
        try {
            StorageHandler.removeLine(taskId, storage.dataFilePath);
        } catch (IOException e) {
            Ui.formatPrint("Error while deleting task from data file.");
        }
    }
}
