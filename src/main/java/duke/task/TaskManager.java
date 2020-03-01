package duke.task;

import duke.exception.TaskException.TaskAlreadyMarkedException;
import duke.task.storage.TaskLoader;
import duke.task.tasktypes.Task;
import duke.task.storage.TaskRecorder;
import duke.ui.Ui;
import duke.exception.TaskException.TaskOutOfBoundsException;
import duke.exception.TaskException.TaskListEmptyException;
import java.util.ArrayList;

import static duke.Duke.inDebugMode;

/**
 * A class representing a task manager. It works with {@link Task} instances
 * to add, remove, list, find, load, save, or mark tasks.
 */
public class TaskManager {

    /** List with all the tasks in the program */
    private ArrayList<Task> tasks;

    /** Helps display the output to the user */
    private Ui printer;

    /** Deal with storage of tasks (both loading and recording tasks) */
    private TaskRecorder recorder;
    private TaskLoader loader;

    public TaskManager () {
        tasks = new ArrayList<>();
        printer = new Ui();

        if (!inDebugMode) {
            recorder = new TaskRecorder(printer);
            loader = new TaskLoader(printer);
        }
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The object already created based on the command type.
     */
    public void addTask (Task task) {
        tasks.add(task);

        if (!inDebugMode) {
            recorder.recordAllTasks(tasks);
        }
    }

    /**
     * Marks a task as done if possible.
     *
     * @param taskIndex The list number of the task to mark as done.
     * @return Task marked as done.
     * @throws TaskListEmptyException If list tasks is empty.
     * @throws TaskOutOfBoundsException If taskIndex is out of bounds.
     *
     */
    public Task markTaskAsDone (int taskIndex) throws TaskListEmptyException, TaskOutOfBoundsException,
            TaskAlreadyMarkedException {

        if (tasks.isEmpty()) {
            throw new TaskListEmptyException();
        }

        if (taskIndexOutOfBounds(taskIndex)) {
            throw new TaskOutOfBoundsException(taskIndex);
        }

        if (tasks.get(taskIndex).getCompletionStatus()) {
            throw new TaskAlreadyMarkedException(tasks.get(taskIndex));
        }

        tasks.get(taskIndex).setTaskAsDone();

        if (!inDebugMode) {
            recorder.recordAllTasks(tasks);
        }

        return tasks.get(taskIndex);
    }


    /**
     * Removes a task from the tasks list.
     *
     * @param taskIndex Index of the task to remove.
     * @return Task removed from the list.
     * @throws TaskOutOfBoundsException If taskIndex is out of bounds.
     * @throws TaskListEmptyException If tasks list is empty.
     */
    public Task removeTask (int taskIndex) throws TaskOutOfBoundsException, TaskListEmptyException {

        if (tasks.isEmpty()) {
            throw new TaskListEmptyException();
        }

        if (taskIndexOutOfBounds(taskIndex)) {
            throw new TaskOutOfBoundsException(taskIndex);
        }

        Task toPrint = tasks.get(taskIndex);
        tasks.remove(taskIndex);

        // Record tasks before returning
        if (!inDebugMode) {
            recorder.recordAllTasks(tasks);
        }

        return toPrint;
    }


    /**
     * Constructs a list of all tasks to be displayed.
     *
     * @return Constructed string containing all tasks.
     * @throws TaskListEmptyException If tasks list is empty.
     */
    public String listTasks () throws TaskListEmptyException {
        if (tasks.isEmpty()) {
            throw new TaskListEmptyException();
        }

        String msg = "Here is your list so far:" + System.lineSeparator() + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {

            if (i == tasks.size() - 1) {
                msg += "\t" + (i + 1) + "." + tasks.get(i);
                continue;
            }
            msg += "\t" + (i + 1) + "." + tasks.get(i) + System.lineSeparator();

        }

        return msg;
    }


    /**
     * Loads tasks, if any, from the data file.
     */
    public void loadTasks () {
        if (!inDebugMode) {
            this.tasks = loader.loadTasks();
        }
    }


    /**
     * Checks if the given taskIndex is within bounds.
     *
     * @param taskIndex Task index given by the user.
     * @return Boolean to tell whether task is within bounds.
     */
    private boolean taskIndexOutOfBounds (int taskIndex) {
        return (taskIndex >= tasks.size() || taskIndex < 0);
    }

    public int getListSize () {
        return tasks.size();
    }

    /**
     * Gets the list noun, 'task' or 'tasks', based on the list size.
     *
     * @return Tasks list noun.
     */
    public String getTaskListNoun () {
        String singularNoun = "task";
        String pluralNoun = "tasks";

        return (tasks.size() > 1) ? pluralNoun: singularNoun;
    }

    /**
     * Gets the task list noun description, 'is' or 'are', based on the list size.
     *
     * @return Noun descriptor.
     */
    public String getTaskListNounDescriptor () {
        String singularDescriptor = "is";
        String pluralDescriptor = "are";

        return (tasks.size() > 1) ? pluralDescriptor: singularDescriptor;
    }
}
