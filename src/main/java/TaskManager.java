import java.util.ArrayList;

public class TaskManager {

    /** List with all the tasks in the program */
    private ArrayList<Task> tasks;

    /** Helps display the output to the user */
    private Output printer;

    public TaskManager () {
        tasks = new ArrayList<Task>();
        printer = new Output();
    }

    /**
     * Adds a new task to the list
     *
     * @param task The object already created based on the
     *             command type
     */
    public void addTask (Task task) {
        tasks.add(task);
        printer.printTaskAdded(tasks.size(), task);
    }

    /**
     * Marks a task as done if possible
     *
     * @param taskIndex The list number of the task to mark as done
     */
    public void markTaskAsDone (int taskIndex) {

        if (taskIndex <= 0 || tasks.size() == 0) {
            printer.printMarkNegativeIndex();

        } else if (taskIndex > tasks.size()) {
            printer.printMarkGreaterThanIndex(tasks.size());

        } else {

            if (!tasks.get(taskIndex - 1).getCompletionStatus()) {
                tasks.get(taskIndex - 1).setTaskAsDone();
                printer.printMarkedTask(tasks.get(taskIndex - 1));

            } else {
                printer.printTaskAlreadyMarked(tasks.get(taskIndex - 1));

            }
        }
    }

    /**
     * Lists all the user tasks in the program
     */
    public void listTasks () {
        if (tasks.isEmpty()) {

            printer.printListIsEmpty();
            return;
        }

        printer.printList(tasks);
    }

}
