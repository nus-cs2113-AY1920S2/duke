package duke;

import java.util.ArrayList;

/**
 * Contains the task list and the operations used to manipulate the list.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Initialise the list with the given task list.
     * @param tasks A given task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Initialise an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * List the tasks on the screen.
     * @param ui The ui object used to show the tasks.
     */
    public void listTasks(Ui ui) {
        if (tasks.size() == 0) {
            ui.printFormattedString(Ui.NO_TASKS);
        } else {
            ui.printTasks(tasks);
        }
    }

    /**
     * Mark a task as done and inform the user of the change.
     * @param ui The ui object used to inform the user.
     * @param taskIndex The index of the task being marked as done.
     */
    public void checkOffTask(Ui ui, int taskIndex) {
        tasks.get(taskIndex - 1).markAsDone();
        ui.printDoneTask(tasks, taskIndex);
    }

    public void deleteTask(Ui ui, int taskIndex) {
        ui.printDeletedTask(tasks, taskIndex);
    }

    /**
     * Add a todo to the list and inform the user.
     * @param description The description of the todo.
     * @param ui The ui object used to inform the user.
     */
    public void addTodo(String description, Ui ui) {
        tasks.add(new ToDo(description));
        ui.printAddedTask(tasks);
    }

    /**
     * Add a deadline to the list and inform the user. The description and
     * deadline are retrieved from the string given.
     * @param info The string containing the description and deadline.
     * @param ui The ui object used to inform the user.
     */
    public void addDeadline(String info, Ui ui) {
        int dividerIndex = info.indexOf("/by");
        String description = info.substring(0, (dividerIndex - 1));
        String by = info.substring(dividerIndex + 4);
        tasks.add(new Deadline(description, by));
        ui.printAddedTask(tasks);
    }

    /**
     * Add an event to the list and inform the user. The description and
     * timeslot are retrieved from the string given.
     * @param info The string containing the description and timeslot.
     * @param ui The ui object used to inform the user.
     */
    public void addEvent(String info, Ui ui) {
        int dividerIndex = info.indexOf("/at");
        String description = info.substring(0, (dividerIndex - 1));
        String at = info.substring(dividerIndex + 4);
        tasks.add(new Event(description, at));
        ui.printAddedTask(tasks);
    }

    public void findTasks(Ui ui, String keyword) {
        ArrayList<String> foundTasks = new ArrayList<>();
        int i = 1;
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(i + "." + task.toString());
            }
            i++;
        }
        if (foundTasks.size() == 0) {
            ui.printFormattedString(Ui.NO_TASKS);
        } else {
            ui.printFoundTasks(foundTasks);
        }
    }

    public void clearTasks(Ui ui, TaskList taskList, Storage storage) {
        if (ui.isClear()) {
            tasks.clear();
            storage.saveTaskstoDisk(ui, taskList);
        }
    }
}
