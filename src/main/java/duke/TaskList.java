package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> t){
        this.taskList = t;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}