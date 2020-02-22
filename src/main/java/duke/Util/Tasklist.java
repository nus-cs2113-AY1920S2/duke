package duke.Util;

import duke.taskmanager.Tasks;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Tasklist {
    public static List<Tasks> tasks = new ArrayList<>();
    private Load load;
    public Tasklist() throws FileNotFoundException {
        Load load = new Load(Paths.get("data/myTasks.txt"));
        tasks = load.readData();
    }

    public static void find(String input) {
        int index = 0;
        for (Tasks task : tasks) {
            if (task.getTask().contains(input)) {
                System.out.println("    "+ index + ": " +
                        task);
            }
            index ++;
        }
    }

    public static void saveTaskList() {
        Storage.writeData(tasks);
    }
    public static List<Tasks> getTasks() {
        return tasks;
    }
    public static Tasks getTask(int index) {
        return tasks.get(index);
    }
    public static void add(Tasks task) {
        tasks.add(task);
    }

    public static void delete(int index) {
        tasks.remove(index);
        Storage.writeData(tasks);
    }

    public static int getSize() {
        return tasks.size();
    }

    public static void showList() {
        System.out.println("    Your current task list:");
        if (getSize() == 0){
            System.out.println("    You have no ongoing task.");
        } else {
            int index = 0;
            for (Tasks task : tasks) {
                System.out.println("    " + index + ". " + task.toString());
                index++;
            }
        }
    }
}
