package duke.task;

import duke.exception.DukeTaskIdInvalidException;

import java.util.ArrayList;

/**
 *  A container class which contains the a list of Task objects.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Default constructor.
     * Initialize a new ArrayList of Task objects, initially empty.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Return an ArrayList of Task objects.
     * @return an ArrayList of Task objects.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Constructor with an ArrayList of Task objects provided.
     *
     * @param list a list of the Task objects.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Add new task to the list.
     *
     * @param task the Task object to be added.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Delete a task from the list.
     *
     * @param taskId the id of the task which the user intends to delete
     * @throws DukeTaskIdInvalidException exception is thrown when the task id provided by the user is invalid.
     */
    public void delete(int taskId) throws DukeTaskIdInvalidException {
        try {
            list.remove(taskId);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskIdInvalidException();
        }
    }

    /**
     * Get the specific Task object with the given task id.
     *
     * @param taskId the id the the specific task.
     * @return the Task object whichever is looking for.
     */
    public Task getTask(int taskId) {
        return list.get(taskId);
    }

    /**
     * Get the number of tasks the user has.
     *
     * @return the size of the task list.
     */
    public int getSize() {
        return list.size();
    }
}
