package data;

import data.task.Task;
import data.task.TaskList;

/**
 * Represents the entire address book. Contains the data of the address book.
 */
public class Duke {

    private TaskList allTasks;

    /**
     * Creates an empty address book.
     */
    public Duke() {
        this.allTasks = new TaskList();
    }

    public TaskList getTaskList() {
        return allTasks;
    }


    /**
     * Adds a person to the address book.
     */
    public void addTask(Task toAdd) {
        allTasks.add(toAdd);
    }

    /**
     * Returns true if an equivalent person exists in the address book.
     */
    public boolean containsTask(Task key) {
        return allTasks.contains(key);
    }


    /**
     * Clears all persons and tags from the address book.
     */
    public void clear() {
        allTasks.clear();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Duke // instanceof handles nulls
                && this.allTasks.equals(((Duke) other).allTasks));
    }

    /**
     * Set target index task as done.
     */
    public void doneTask(int doneId) {
        //find the task
        allTasks.getInternalList().get(doneId).setDone(true);
    }
}
