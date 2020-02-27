import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;

import java.io.Serializable;
import java.util.ArrayList;

//TODO Abstract this class further

/**
 * <h1>TaskList</h1>
 * Contains all functions related to the Task List, such as:
 * adding, deleting and listing tasks in the task list,
 */
public class TaskList implements Serializable{
    public static ArrayList<Task> taskList;

    /**
     * Initialises an ArrayList of Task type for the TaskList
     * @param currTask the current task list retrieved from Storage
     */
    public TaskList(ArrayList<Task> currTask) {
        taskList = currTask;
    }

    /**
     * Retrieves the ArrayList of tasks
     * @return the task ArrayList
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Lists all the tasks in the task list
     */
    protected static void listTasks() {
        if (taskList.size() == 0) {
            System.out.println("List is empty!");
        }
        else {
            System.out.println("Current task list: ");
            int index = 1;
            for (Task t : taskList) {
                System.out.println("\t" +index +". " +t.toString());
                index++;
            }
        }
    }

    /**
     * Marks the given task as done
     * @param userCmd the given user command
     * @throws DukeException of NumberFormatException if the number is not an integer
     * @throws DukeException of ArrayIndexOutOfBoundsException if no number is provided
     */
    protected static void markTaskDone(String userCmd) throws DukeException {
        // syntax: done 2
        String[] splitCmd = userCmd.split(" ");
        int completedTask = 0;

        // error check syntax
        try {
            completedTask = Integer.parseInt(splitCmd[1]);
        }
        catch (NumberFormatException e){
            throw new DukeException("The number must be in number form. Syntax: done 3");
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("There must be a task number provided to mark as done. Syntax: done 3");
        }

        if (completedTask > taskList.size() ) {
            System.out.println("Task not found!");
        }
        else {
            int completedIndex = completedTask-1;
            Task t = taskList.get(completedIndex);
            // todo: need to check if already completed?
            t.setDone();

            System.out.println("I've marked this task as done:");
            System.out.println("\t" +t.toString());
        }
    }

    /**
     * Adds a To-do task to the current task list
     * @param userCmd the user command stating the given task
     * @throws DukeException of ArrayIndexOutOfBoundsException if no description is given
     */
    protected static void addTask(String userCmd) throws DukeException {
        String todoStr;
        try{
            String[] splitCmd = userCmd.split("todo ");
            todoStr = splitCmd[1].trim();
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("There must be a description for a todo task. Syntax: todo buy food");
        }

        Task newTask = new Task(todoStr);
        taskList.add(newTask);
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t" +newTask.toString() );

    }

    /**
     * Adds a Deadline task to the current task list
     * @param userCmd the user command stating the given deadline
     * @throws DukeException of ArrayIndexOutOfBoundsException if a wrong description was given
     */
    protected static void addDeadline(String userCmd) throws DukeException {
        String taskStr, deadlineStr;
        // Syntax: deadline return book /by Sunday
        try {
            String[] splitCmd = userCmd.split("/by ");
            deadlineStr = splitCmd[1].trim();
            String[] splitName = splitCmd[0].split("deadline ");
            taskStr = splitName[1].trim();
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("Wrong syntax for deadline tasks. Syntax: deadline buy food /by Sunday");
        }

        Deadline newDeadline = new Deadline(taskStr, deadlineStr);
        taskList.add(newDeadline);

        System.out.println("Got it. I've added this task: ");
        System.out.println("\t" +newDeadline.toString() );

    }

    /**
     * Adds an Event task to the current task list
     * @param userCmd the user command stating the given event
     * @throws DukeException of ArrayIndexOutOfBoundsException if a wrong description was given
     */
    protected static void addEvent(String userCmd) throws DukeException {
        // Syntax: event project meeting /at Mon 2-4pm
        String dateStr, eventStr;

        try {
            String[] splitCmd = userCmd.split("/at ");
            dateStr = splitCmd[1].trim();

            String[] splitName = splitCmd[0].split("event ");
            eventStr = splitName[1].trim();
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("Wrong syntax for event tasks. Syntax: event food fair /at Mon 2-4pm");
        }

        Event newEvent = new Event(eventStr, dateStr);
        taskList.add(newEvent);
        System.out.println("Got it. I've added this event: ");
        System.out.println("\t" +newEvent.toString() );
    }

    /**
     * Removes the given task from the current task list
     * @param userCmd the user command stating the task to be deleted
     * @throws DukeException of NumberFormatException if the task number is not an integer
     * @throws DukeException of ArrayIndexOutOfBoundsException if a no task number is given
     */
    protected static void deleteTask(String userCmd) throws DukeException {
        // syntax: done 2
        String[] splitCmd = userCmd.split(" ");
        int taskToDelete = 0;

        // error check syntax
        try {
            taskToDelete = Integer.parseInt(splitCmd[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("The number must be in number form. Syntax: delete 3");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("There must be a task number provided to mark as done. Syntax: delete 3");
        }

        if (taskToDelete > taskList.size()) {
            System.out.println("Task not found!");
        } else {
            int taskIndex = taskToDelete - 1;
            Task t = taskList.get(taskIndex);
            taskList.remove(taskIndex);

            System.out.println("I've removed this task:");
            System.out.println("\t" + t.toString());
            System.out.println("You have " + taskList.size() + " task(s) left.");
        }
    }
}
