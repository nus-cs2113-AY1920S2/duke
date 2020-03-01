package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Stores tasks in a task list.
 * Contains an ArrayList object as the task list.
 * Has constructor and getter methods for the task list.
 *
 * @see Task
 */

public class TaskList {

    private static ArrayList<Task> taskList;


    public TaskList(ArrayList<Task> t){
        this.taskList = t;
    }


    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}