package duke.task;

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
     * Constructor with an ArrayList of Task objects provided.
     *
     * @param list a list of the Task objects.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Return an ArrayList of Task objects.
     * @return an ArrayList of Task objects.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Add new task to the list.
     *
     * @param task the Task object to be added.
     */
    public void add(Task task) {
        list.add(task);
    }


}
