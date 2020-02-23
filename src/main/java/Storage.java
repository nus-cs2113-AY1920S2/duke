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

    /**
     * Returns the task at index that the User specified.
     * User-specified index will need to subtract 1 as we are using 0 indexing.
     *
     * @param index Index that the User specified.
     * @return Task at index - 1, if it exists
     */
    public Task getTask(int index) {

        return myTasks.get(index - 1);
    }

    /**
     * Returns the current size of the tasks storage
     *
     * @return current size of storage
     */
    public static int getSize() {
        return myTasks.size();
    }

    /**
     * Deletes the task at index that the User specified.
     * User-specified index will need to subtract 1 as we are using 0 indexing.
     *
     * @param index Index that the User specified.
     */
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
