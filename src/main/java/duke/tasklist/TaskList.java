package duke.tasklist;

import duke.ui.Ui;
import duke.exceptions.BadFileFormatException;
import duke.exceptions.BadLineFormatException;
import duke.exceptions.BadTaskChoiceFormatException;
import duke.storage.Storage;
import duke.tasks.Task;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;

/**
 * A class to store all the tasks and perform operations to/on the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private String filePath;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(String filePath) {
        this();
        this.filePath = filePath;
        try {
            ArrayList<String> fileContents = Storage.loadFromFile(filePath);
            readFileToTasks(fileContents);
        } catch (FileNotFoundException e) {
            Ui.printPretty("Couldn't locate a save file. Starting with an empty list of tasks");
        } catch (BadFileFormatException e) {
            Ui.printPretty(e.getMessage());
        }

        if (tasks.size() != 0) {
            listTasks();
        }
    }

    /**
     * Add a task to the list of tasks
     * @param newTask the task to be added
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
        String message = "Added this task:" + System.lineSeparator() + newTask.toString() + System.lineSeparator() +
                "Now you have " + tasks.size() + " tasks in the list";
        Ui.printPretty(message);
    }

    /**
     * Print a list of all the tasks
     */
    public void listTasks() {
        String message = "These are your tasks:" + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            String lineEnd = i == tasks.size() - 1 ? "" : System.lineSeparator(); // Do this so no extra newline
            message += (String.format("%d. %s", i + 1, tasks.get(i).toString()) + lineEnd);
        }
        Ui.printPretty(message);
    }

    /**
     * Delete a task specified by <code>taskIndex</code>
     * @param taskIndex the index of the task to be deleted
     * @throws BadTaskChoiceFormatException if <code>taskIndex</code> is out of bounds of the list of tasks
     */
    public void deleteTask(int taskIndex) throws BadTaskChoiceFormatException {
        if (taskIndex > tasks.size() - 1 || taskIndex < 0) {
            throw new BadTaskChoiceFormatException("Task number is invalid");
        }

        Task deletedTask = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        Ui.printPretty("Deleted task " + (taskIndex + 1) + ":\n" + deletedTask.toString() + "\nNow you have "
                + tasks.size() + " tasks left in your list");
    }

    /**
     * Mark a task specified by <code>taskIndex</code> as done
     * @param taskIndex the index of the task to be marked as done
     * @throws BadTaskChoiceFormatException if <code>taskIndex</code> is out of bounds of the list of tasks
     */
    public void markAsDone(int taskIndex) throws BadTaskChoiceFormatException {
        if (taskIndex > tasks.size() - 1 || taskIndex < 0) {
            throw new BadTaskChoiceFormatException("Task number is invalid");
        }

        Task task = tasks.get(taskIndex);
        task.setIsDone(true);
        Ui.printPretty("Task " + (taskIndex + 1) + " has been marked as done\n" + task.toString());
    }

    /**
     * Save the list of tasks to file
     */
    public void writeTasksToFile() {
        try {
            Storage.saveToFile(filePath, getFormattedTasks());
        } catch (IOException e) {
            Ui.printPretty(e.getMessage() + System.lineSeparator() +
                    "*** Writing to file failed. Your task list may not have been saved. ***");
        }
    }

    /**
     * Convert the list of tasks as a formatted string so that it can be saved
     * @return a string compliant with the format for saving to file
     */
    private String getFormattedTasks() {
        String formattedTasks = "";
        for (Task t : tasks) {
            formattedTasks += t.toFormattedString() + System.lineSeparator();
        }
        return formattedTasks;
    }

    /**
     * Populate the tasks list with contents from file
     * @param fileContents file contents split line by line
     * @throws BadFileFormatException if the file was not formatted correctly
     */
    private void readFileToTasks(ArrayList<String> fileContents) throws BadFileFormatException {
        int lineCounter = 1;
        String errors = "";

        for (String s : fileContents) {
            try {
                tasks.add(Task.getTaskFromFormattedLine(s));
            } catch (BadLineFormatException e) {
                errors += e.getMessage() + " on line " + lineCounter + " of " + filePath + System.lineSeparator();
            }
            lineCounter++;
        }

        if (!errors.equals("")) {
            throw new BadFileFormatException(errors.substring(0, errors.length() - 1));
        }
    }
}

