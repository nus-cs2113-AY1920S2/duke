package duke;

import duke.exception.EmptyListException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;

import java.util.ArrayList;

/**
 * TaskList class for managing all operations related to list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Default constructor for TaskList class. Instantiate a new ArrayList Object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for existing task list.
     * @param tasks ArrayList containing existing tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns string indicating the whether task is in Singular or Plural form.
     * @param numTasks Number of tasks
     * @return String containing task if singular or tasks if plural
     */
    public String taskValidator(int numTasks) {
        String totalTasks = Integer.toString(numTasks);
        if (numTasks <= 1) {
            return "You now have " + totalTasks + " task in the list";
        } else {
            return "You now have " + totalTasks + " tasks in the list";
        }
    }

    /**
     * Getter for size of ArrayList
     * @return ArrayList size
     */
    public int getListSize() {
        return tasks.size();
    }

    /**
     * Getter for ArrayList of tasks
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getTaskArray() {
        return this.tasks;
    }

    /**
     * Adds a Todo into tasks ArrayList.
     * @param todoTask String of Todo task
     */
    public void addTodo(String todoTask) {
        Todo todo = new Todo(todoTask);
        tasks.add(todo);
        System.out.println("Got it! You've added a todo task: ");
        System.out.println(todo.toString());
        System.out.println(taskValidator(tasks.size()));
    }

    /**
     * Adds an Event into tasks ArrayList.
     * @param eventTask String of Event task
     * @param at String of Date and Time of where event will happen
     */
    public void addEvent(String eventTask, String at) {
        Event event = new Event(eventTask, at);
        tasks.add(event);
        System.out.println("Got it! You've added an event task: ");
        System.out.println(event.toString());
        System.out.println(taskValidator(tasks.size()));
    }

    /**
     * Adds a Deadline into tasks ArrayList
     * @param deadlineTask String of Deadline Task
     * @param by String of Date and Time task is due
     */
    public void addDeadline(String deadlineTask, String by) {
        Deadline deadline = new Deadline(deadlineTask, by);
        tasks.add(deadline);
        System.out.println("Got it! You've added a deadline task: ");
        System.out.println(deadline.toString());
        System.out.println(taskValidator(tasks.size()));
    }

    /**
     * Lists tasks in tasks ArrayList
     * @throws EmptyListException If tasks ArrayList is empty
     */
    public void listTasks() throws EmptyListException {
        int sizeofArray = tasks.size();
        if (sizeofArray != 0) {
            System.out.println("Here are the tasks on your list: ");
            for (int i = 1; i < sizeofArray + 1; i++) {
                String taskNum = Integer.toString(i);
                System.out.println(taskNum + "." + tasks.get(i - 1));
            }
        } else {
            throw new EmptyListException("There are no tasks in the list! Please add some tasks!");
        }
    }

    /**
     * Deletes tasks from ArrayList using index number of tasks.
     * @param taskNum Index number of task to be deleted
     */
    public void deleteTask(int taskNum) {
        System.out.println("Got it! I've removed this task: ");
        System.out.println(tasks.get(taskNum - 1).toString());
        tasks.remove(taskNum - 1);
        System.out.println(taskValidator(tasks.size()));
    }

    /**
     * Marks tasks from ArrayList as done.
     * Indicated using index number of tasks.
     * @param taskNum Index number of task to be marked as done
     */
    public void markDone(int taskNum) {
        tasks.get(taskNum - 1).markAsDone();
        System.out.println("Awesome! I've marked the following task as done:");
        System.out.println(tasks.get(taskNum - 1).toString());

    }

    /**
     * Finds tasks from tasks ArrayList based on key words supplied.
     * @param findString String in task description that is to be searched
     */
    public void findTask(String findString) {
        ArrayList<Integer> matchList = new ArrayList<>();
        for (int i = 1; i < tasks.size() + 1; i++) {
            if (tasks.get(i-1).toString().contains(findString)) {
                matchList.add(i);
            }
        }

        if (matchList.size() > 0) {
            System.out.println("I've found the follow tasks matching your search: ");
            for (int i = 0; i < matchList.size(); i++) {
                String index = matchList.get(i).toString();
                System.out.println(index + "." + tasks.get(matchList.get(i) - 1));
            }
        } else {
            System.out.println("Sorry! I could not find any tasks matching your searches!");
        }
        
    }

}
