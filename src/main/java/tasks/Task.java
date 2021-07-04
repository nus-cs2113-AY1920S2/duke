package tasks;

import java.io.Serializable;

/**
 * <h1>Task</h1>
 * Contains the general properties of a Task:
 * Name, Task type, completion status
 *
 * <h3>Also known as To-Do tasks</h3>
 * To-Dos are tasks without any date/time attached to it
 * e.g. visit zoo
 */
public class Task implements Serializable{
    protected String taskName;
    protected boolean isDone;
    protected char taskType;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskType = 'T';
    }

    /**
     * Gets the type of the Task
     * @return 'T' if it is a To-Do task; 'D' for Deadline tasks, 'E' for Event tasks
     */
    public char getTaskType() {
        return taskType;
    }

    /**
     * Gets the status 'icon' of the task
     * @return 'Y' if it is complete, else 'N'
     */
    public String getStatusIcon(){
        return (isDone ? "Y" : "N");
//        return (isDone ? "\u2713" : "\u2718"); check : cross mark

    }

    /**
     * Gets the name of the task
     * @return the task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the isDone variable to true, indicating the task is completed
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Converts the task description to a String-readable format
     * @return the task details in a readable String
     */
    public String toString() {
        return ("[" +getTaskType() +"][" + getStatusIcon() +"] " +getTaskName() );
    }
}
