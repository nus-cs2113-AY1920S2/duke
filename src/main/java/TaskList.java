import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskType;

import java.util.ArrayList;

/*
Contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    public static ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    protected static ArrayList<Task> markTaskDone(String userCmd, ArrayList<Task> taskArrList) throws DukeException {
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

        if (completedTask > taskArrList.size() ) {
            System.out.println("Task not found!");
        }
        else {
            int completedIndex = completedTask-1;
            Task t = taskArrList.get(completedIndex);
            // todo: need to check if already completed?
            t.setDone();

            System.out.println("I've marked this task as done:");
            System.out.println("\t" +t.toString());
        }
        return taskArrList;
    }

    protected static ArrayList<Task> addTask(String userCmd, ArrayList<Task> taskArrList) throws DukeException {
        String todoStr;
        try{
            String[] splitCmd = userCmd.split("todo ");
            todoStr = splitCmd[1].trim();
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("There must be a description for a todo task. Syntax: todo buy food");
        }

        Task newTask = new Task(TaskType.TODO, todoStr);
        taskArrList.add(newTask);
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t" +newTask.toString() );

        return taskArrList;
    }

    protected static ArrayList<Task> addDeadline(String userCmd, ArrayList<Task> taskArrList) throws DukeException {
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

        Deadline newDeadline = new Deadline(TaskType.DEADLINE, taskStr, deadlineStr);
        taskArrList.add(newDeadline);

        System.out.println("Got it. I've added this task: ");
        System.out.println("\t" +newDeadline.toString() );

        return taskArrList;
    }

    protected static ArrayList<Task> addEvent(String userCmd, ArrayList<Task> taskArrList) throws DukeException {
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

        Event newEvent = new Event(TaskType.EVENT, eventStr, dateStr);
        taskArrList.add(newEvent);
        System.out.println("Got it. I've added this event: ");
        System.out.println("\t" +newEvent.toString() );

        return taskArrList;
    }

    protected static ArrayList<Task> deleteTask(String userCmd, ArrayList<Task> taskArrList) throws DukeException {
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

        if (taskToDelete > taskArrList.size()) {
            System.out.println("Task not found!");
        } else {
            int taskIndex = taskToDelete - 1;
            Task t = taskArrList.get(taskIndex);
            taskArrList.remove(taskIndex);

            System.out.println("I've removed this task:");
            System.out.println("\t" + t.toString());
            System.out.println("You have " + taskArrList.size() + " task(s) left.");
        }
        return taskArrList;
    }
}
