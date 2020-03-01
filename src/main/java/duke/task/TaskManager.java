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


public class TaskManager {

    /** List with all the tasks in the program */
    private ArrayList<Task> tasks;

    /** Helps display the output to the user */
    private Ui printer;
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
     * Adds a new task to the list
     *
     * @param task The object already created based on the
     *             command type
     */
    public void addTask (Task task) {
        tasks.add(task);

        if (!inDebugMode) {
            recorder.recordAllTasks(tasks);
        }
    }

    /**
     * Marks a task as done if possible
     *
     * @param taskIndex The list number of the task to mark as done
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
     * Lists all the user tasks in the program
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

    public void loadTasks () {
        if (!inDebugMode) {
            this.tasks = loader.loadTasks();
        }
    }

    private boolean taskIndexOutOfBounds (int taskIndex) {
        return (taskIndex >= tasks.size() || taskIndex < 0);
    }

    public int getListSize () {
        return tasks.size();
    }

    public String getTaskListNoun () {
        return (tasks.size() > 1) ? "tasks": "task";
    }

    public String getTaskListNounDescriptor () {
        return (tasks.size() > 1) ? "are": "is";
    }
}
