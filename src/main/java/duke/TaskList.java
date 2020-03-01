package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.exceptions.EmptyListException;
import java.util.ArrayList;

/**
 * TaskList class to handle all TaskList operations.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for existing task list.
     * @param tasks ArrayList consisting of all existing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get size of ArrayList.
     * @return size of ArrayList.
     */
    public int sizeOfList() {
        return tasks.size();
    }

    /**
     * Get tasks of ArrayList.
     * @return tasks of ArrayList.
     */
    public ArrayList<Task> getTaskArray() {
        return this.tasks;
    }

    /**
     * Adds Todo into tasks ArrayList.
     * @param taskToAdd String of task to add.
     */
    public void todoTask(String taskToAdd) {
        Todo todo = new Todo(taskToAdd);
        tasks.add(todo);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks.get(tasks.size() - 1) + System.lineSeparator()
                + "You now have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Finds tasks from tasks ArrayList.
     * @param taskToFind String of task to find.
     */
    public void findCommand(String taskToFind) {
        ArrayList<Integer> resultsList = new ArrayList<>();
        for (int i = 1; i < tasks.size() + 1; i++) {
            if (tasks.get(i-1).toString().contains(taskToFind)) {
                resultsList.add(i);
            }
        }
        if (resultsList.size() > 0) {
            System.out.println("Here are the matching tasks in your list: ");
            for (int i = 0; i < resultsList.size(); i++) {
                String index = resultsList.get(i).toString();
                System.out.println(index + "." + tasks.get(resultsList.get(i) - 1));
            }
        } else {
            System.out.println("Sorry! There are no tasks matching your search.");
        }
    }

    /**
     * Adds Event into tasks ArrayList.
     * @param eventToAdd String of event to add.
     * @param at String of Date of event to be added.
     */
    public void eventTask(String eventToAdd, String at){
        Event event = new Event(eventToAdd, at);
        tasks.add(event);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks.get(tasks.size() - 1) + System.lineSeparator()
                + "You now have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Adds Deadline into tasks ArrayList.
     * @param deadlineToAdd String of deadline to add.
     * @param by String of Date of deadline of task.
     */
    public void deadlineTask(String deadlineToAdd, String by) {
        Deadline deadline = new Deadline(deadlineToAdd, by);
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:" + System.lineSeparator()
                + tasks.get(tasks.size() - 1) + System.lineSeparator()
                + "You now have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Lists all tasks in tasks ArrayList.
     * @throws EmptyListException If there are no existing tasks in ArrayList tasks.
     */
    public void listCommand() throws EmptyListException {
        int sizeOfList = tasks.size();
        if (sizeOfList != 0) {
            System.out.println("Here are the duke.tasks in your list:");
            for (int i = 0; i < sizeOfList ; i += 1) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        } else {
            throw new EmptyListException("No duke.tasks in the list.");
        }
    }

    /**
     * Deletes tasks from ArrayList tasks.
     * @param index Index of tasks to be deleted.
     */
    public void deleteCommand(int index){
        System.out.println("Got it! I've removed this task: ");
        System.out.println(tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.println( "You now have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Marks specified tasks as done.
     * @param index Index  of task to be marked.
     */
    public void doneCommand(int index){
        tasks.get(index - 1).updateIsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(index - 1));
    }
}
