package task;

import exception.DukeException;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /** Initializes the task list. */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** Returns the size of the task list. */
    public int getSize() {
        return tasks.size();
    }

    /** Returns the task at a given index. */
    public Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task item does not exist.");
        }
    }

    /** Returns the tasks currently stored in the application. */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /** Deletes the task at a given index. */
    public void deleteTask(int index) throws DukeException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task item does not exist.");
        }
    }

    /** Gets the latest task added. */
    public Task getLatestTask() {
        return tasks.get(tasks.size() - 1);
    }

    /** Adds a task into the application. */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /** Sets a task at the given index as done. */
    public void doneTask(int index) throws DukeException {
        this.getTask(index).markAsDone();
    }
}
