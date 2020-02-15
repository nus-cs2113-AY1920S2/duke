package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskManager {
    public static final int MAXIMUM_TASK = 100;
    public static final String FORMAT_LINE = "------------------------------------";
    public static final String LISTING = "Here is the list of all tasks you have:";
    public static final String DONE = "Good job! You have done this task:";
    private Task[] tasks = new Task[MAXIMUM_TASK];
    private int numOfTasks = 0;

    public void printAddedTask(String str) {
        System.out.println(FORMAT_LINE);
        System.out.println("Got it! I have added this task into your list:");
        System.out.println("\t" + str);
        System.out.println("Now you have " + numOfTasks + (numOfTasks > 1? " tasks": " task") + " in the list");
        System.out.println("Remember to finish your tasks on time!");
        System.out.println(FORMAT_LINE);
        System.out.println();
    }

    public void addTodo(String command) {
        tasks[numOfTasks++] = new Todo(command);
        printAddedTask(tasks[numOfTasks-1].print());
    }

    public void addDeadline(String command) {
        String[] commands = command.split("/", 2);
        tasks[numOfTasks++] = new Deadline(commands);
        printAddedTask(tasks[numOfTasks-1].print());
    }

    public void addEvent(String command) {
        String[] commands = command.split("/", 2);
        tasks[numOfTasks++] = new Event(commands);
        printAddedTask(tasks[numOfTasks-1].print());
    }

    public void listTask() {
        System.out.println(FORMAT_LINE);
        System.out.println(LISTING);
        for(int i = 0; i < numOfTasks; i++) {
            System.out.println(i+1 + "." + tasks[i].print());
        }
        System.out.println(FORMAT_LINE);
        System.out.println();
    }

    public void markTask(int taskNo) {
        Duke.printFormat(DONE+"  [✓] " + tasks[taskNo-1].getName());
        tasks[taskNo-1].changeStatus(true);
    }
}
