import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    /**
     * Stores all of the user's task and opens previous data from file
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.tasks = Storage.openData();
    }

    /**
     * Stores task list into text file
     */
    public void exitAndSave() {
        Storage.saveData(tasks);
    }

    /**
     * Set user task as done
     *
     * @param taskNum The task number for the task to be set as done
     */
    public void setTaskDone(String taskNum) throws DukeException {
        try {
            int taskNumInt = Integer.parseInt(taskNum);
            taskNumInt--;
            tasks.get(taskNumInt).setDone(true);
            ui.printDone(tasks.get(taskNumInt));
        } catch (NullPointerException | NumberFormatException e) {
            ui.showError("Please include the task number.");
            throw new DukeException();
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Task number does not exist. Please try again!");
            throw new DukeException();
        }
    }

    /**
     * Get and print the list of tasks
     */
    public void getList() {
        if (tasks.size() == 0) {
            ui.showError("No tasks found!");
        } else {
            ui.printList(tasks, tasks.size());
        }
    }

    /**
     * Add new todo task into the last list
     *
     * @param arr The string array command input by user split by spaces
     */
    public void addTodo(String[] arr) throws DukeException {
        try {
            Todo todo = new Todo(arr[1]);
            tasks.add(todo);
            ui.printConfirm(tasks.get(tasks.size()-1), tasks.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showError("Please include your task.");
            throw new DukeException();
        }
    }

    /**
     * Add new event task into the task list
     *
     * @param arr The string array command input by user split by spaces
     */
    public void addEvent(String[] arr) throws DukeException {
        try {
            String arr2[] = Parser.parseEvent(arr);
            Event event = new Event(arr2[0], arr2[1]);
            tasks.add(event);
            ui.printConfirm(tasks.get(tasks.size()-1), tasks.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showError("Please include your task.");
            throw new DukeException();
        }
    }

    /**
     * Add new deadline task into the task list
     *
     * @param arr The string array command input by user split by spaces
     */
    public void addDeadline(String[] arr) throws DukeException {
        try {
            String arr2[] = Parser.parseDeadline(arr);
            Event event = new Event(arr2[0], arr2[1]);
            tasks.add(event);
            ui.printConfirm(tasks.get(tasks.size()-1), tasks.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showError("Please include your task.");
            throw new DukeException();
        }
    }

    /**
     * Delete a task from the task list
     *
     * @param arr The string array command input by user split by spaces
     */
    public void deleteTask(String[] arr) throws DukeException {
        try {
            int taskNum = Integer.parseInt(arr[1]) - 1;
            ui.printDelete(tasks.get(taskNum), tasks.size());
            tasks.remove(taskNum);
        } catch (NullPointerException | NumberFormatException e) {
            ui.showError("Please include the task number.");
            throw new DukeException();
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Task number does not exist. Please try again!");
            throw new DukeException();
        }
    }

}
