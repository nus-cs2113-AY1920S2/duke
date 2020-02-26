package Duke.data;

import Duke.data.objects.Task;

import java.util.ArrayList;

public class TaskList {
    /** The list of task  */
    private static ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<Task>();
    }

    public ArrayList getList(){
        return tasks;
    }
}
