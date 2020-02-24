package duke.task;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void add(Task task) {
        list.add(task);
    }


}
