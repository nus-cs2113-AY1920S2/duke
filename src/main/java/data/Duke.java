package data;

import data.exceptions.ParseException;
import data.exceptions.TaskNotFoundException;
import data.task.Task;
import data.task.TaskList;

/**
 * Represents the duke system. Contains the data of the all tasks
 */
public class Duke {

    /* the tasks list within the system */
    private TaskList allTasks;

    /**
     * Creates an empty address book.
     */
    public Duke() {
        this.allTasks = new TaskList();
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @return the task list
     */
    public TaskList getTaskList() {
        return allTasks;
    }

    /**
     * add a task
     *
     * @param toAdd target task
     */
    public void addTask(Task toAdd) {
        allTasks.add(toAdd);
    }

    /**
     * check if contains the task
     *
     * @param toSearch target task
     */
    public boolean containsTask(Task toSearch) {
        return allTasks.contains(toSearch);
    }

    /**
     * removes the task
     *
     * @param toRemove target task
     * @throws TaskNotFoundException if target task does not exist
     */
    public void removeTask(Task toRemove) throws TaskNotFoundException {
        allTasks.remove(toRemove);
    }


    /**
     * Clears all persons and tags from the address book.
     */
    public void clear() {
        allTasks.clear();
    }

    /**
     * compare this task with other task
     *
     * @param other target task
     * @return true if tasks are equal
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Duke // instanceof handles nulls
                && this.allTasks.equals(((Duke) other).allTasks));
    }

    /**
     * Set target index task as done.
     * @param doneId target index
     * @throws TaskNotFoundException if target task does not exist
     */
    public void doneTask(int doneId) throws TaskNotFoundException {
        //find the task
        allTasks.getInternalList().get(doneId).setDone(true);
    }

    /**
     * Removes the equivalent person from the address book.
     *
     * @throws TaskNotFoundException if no such Person could be found.
     */
    public void removePerson(Task toRemove) throws TaskNotFoundException {
        allTasks.remove(toRemove);
    }


}