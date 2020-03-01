package duke.data;

import duke.format.DateTime;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static Task get(int index) {
        return taskList.get(index);
    }

    public static int size() {
        return taskList.size();
    }

    public static void add(Task task) {
        taskList.add(task);
    }

    public static void delete(int index)  {
        taskList.remove(index);
    }

    public static boolean doTask(int index) {
        if (!taskList.get(index).getIsDone()) {
            taskList.get(index).setIsDone(true);
            return true;
        }
        return false;
    }

    public static ArrayList<Integer> filter(String searchWord) {
        ArrayList<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < taskList.size(); ++i) {
            String task = taskList.get(i).getTask();
            if (task.contains(searchWord)) {
                indexList.add(i);
            }
        }

        return indexList;
    }

    public static ArrayList<Integer> filterDate(LocalDate date) {
        ArrayList<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < taskList.size(); ++i) {
            DateTime dateTime = taskList.get(i).getDateTime();
            if (dateTime != null && dateTime.isOn(date)) {
                indexList.add(i);
            }
        }

        return indexList;
    }

    public static ArrayList<Integer> filterDateBefore(LocalDate date) {
        ArrayList<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < taskList.size(); ++i) {
            DateTime dateTime = taskList.get(i).getDateTime();
            if (dateTime != null && dateTime.isBefore(date)) {
                indexList.add(i);
            }
        }

        return indexList;
    }

    public static ArrayList<Integer> filterDateAfter(LocalDate date) {
        ArrayList<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < taskList.size(); ++i) {
            DateTime dateTime = taskList.get(i).getDateTime();
            if (dateTime != null && dateTime.isAfter(date)) {
                indexList.add(i);
            }
        }

        return indexList;
    }
}
