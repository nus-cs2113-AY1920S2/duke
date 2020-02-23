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
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    public static int getSize() {
        return myTasks.size();
    }

    public void deleteTask(int index) {
        myTasks.remove(index - 1);
    }

    public List<Task> findTasks(String keyword) {
        List<Task> foundTasks = new ArrayList<>();

        for (Task t : myTasks) {
            t.addIfMatchesKeyword(t, foundTasks, keyword);
        }

        return foundTasks;
    }
}
