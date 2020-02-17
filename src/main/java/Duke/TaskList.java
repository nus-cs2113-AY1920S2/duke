package Duke;

import Duke.TaskTypes.Task;

import java.util.ArrayList;

/**
 * The class to represent the list of tasks itself. This is also where the manipulation of the tasks in the task list is actually done (other classes rely on this class {@link TaskList} to do the actual task list manipulations)
 * <p></p>
 * <p>Not only does it serves to add and view the tasks, it also edit the status of the tasks as well. For example, it can get the number of tasks, mark tasks as done and remove the tasks in the tasklist</p>
 * @see ArrayList
 */
public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    /**
     * Get the list of tasks (which is stored as an <code>ArrayList<Task></code>)
     * @return list of task
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }


    /**
     * Set method for initializing the list of tasks itself
     * @param taskList the new list of tasks
     */
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * To add a task into the task list
     * @param task the {@link Task} object to add into the task list
     * @see Task
     */
    public void addTasks(Task task){
        this.taskList.add(task);
    }

    /**
     * Get the number of tasks in the task list
     * @return the number of tasks present
     */
    public int getNumberOfTask() {
        return taskList.size();
    }

    /**
     * Mark the task chosen by the task number to be done
     * @param i the task number to be marked as done
     */
    public void markTaskAsDone(int i) {
        taskList.get(i).markAsDone();
    }

    /**
     * Remove the task corresponding to the task number given from the list of tasks
     * @param i the task number to be removed
     */
    public void removeTask(int i) {
        taskList.remove(i);
    }
}
