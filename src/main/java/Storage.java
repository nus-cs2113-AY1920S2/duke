import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static List<Task> myTasks = new ArrayList<>();

    public void storeTasks(Task task) {
        myTasks.add(task);
    }

    public void displayTasks() {
        Printer.printTasks(myTasks);
    }

    public Task getTask(int index) {
        try {
            return myTasks.get(index - 1);
        } catch(Exception e) {
            return null;
        }
    }
    public static int getSize() {
        return myTasks.size();
    }
}
