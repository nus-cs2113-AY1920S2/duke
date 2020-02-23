package duke.data;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void addToDo(String task) {
        taskList.add(new ToDo(task));
    }

    public static void addDeadline(String task, String deadline) {
        taskList.add(new Deadline(task, deadline));
    }

    public static void addEvent(String task, String duration) {
        taskList.add(new Event(task, duration));
    }

    public static void delete(int index)  {
        taskList.remove(index);
    }

    public static boolean doTask(int index) {
        if (taskList.get(index).getIsDone()) {
            return true;
        }
        return false;
    }


    public static Task get(int index) {
        return taskList.get(index);
    }

    public static int size() {
        return taskList.size();
    }
}
