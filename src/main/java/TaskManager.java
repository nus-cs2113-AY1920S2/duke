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
    public void markTaskAsDone (int taskIndex) throws TaskException {

        if (tasks.isEmpty()) {
            String msg = "Your list is empty... I cannot mark something that doesn't exist as done >:(";
            throw new TaskException(msg);
        }

        if (taskIndexOutOfBounds(taskIndex)) {
            String msg = String.format("The task number %d doesn't exist. There %s only %d %s",
                    taskIndex + 1, getNounDescriptor(), tasks.size(), getTaskNoun());
            throw new TaskException(msg);
        }

        if (!tasks.get(taskIndex).getCompletionStatus()) {
            tasks.get(taskIndex).setTaskAsDone();
            printer.printMarkedTask(tasks.get(taskIndex));

        } else {
            printer.printTaskAlreadyMarked(tasks.get(taskIndex));
        }

    }

    private boolean taskIndexOutOfBounds (int taskIndex) {
        return (taskIndex >= tasks.size() || taskIndex < 0);
    }

    private String getTaskNoun () {
        return (tasks.size() == 1) ? "task": "tasks";
    }

    private String getNounDescriptor () {
        return (tasks.size() == 1) ? "is": "are";
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
