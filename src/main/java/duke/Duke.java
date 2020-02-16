package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    /**
     * List of all tasks added by the user
     */
    private static types.Task[] taskList;

    /**
     * Number of tasks in taskList
     */
    private static int numTasks;

    /**
     * Line that divides text
     */
    private static final String BAR = "____________________________________";

    /**
     * Maximum number of tasks
     */
    private static final int MAX_TASKS = 100;

    private static final String FILE_PATH = "./duke.txt";

    public static void main(String[] args) throws DukeException {
        introduction();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(BAR);
            String[] splitTask = command.split(" ");
            String cWord = splitTask[0];
            String task = "";
            for (int i = 1; i < splitTask.length; i++) {
                task += " " + splitTask[i];
            }
            if (command.equals("list")) {
                listCommand();
            } else if (cWord.equals("done")) {
                try {
                    doneCommand(command);
                } catch (DukeException e){
                    System.out.println("    You must include the number of the completed task!");
                }
            } else if (cWord.equals("todo")) {
                try {
                    todoCommand(task);
                } catch (DukeException e){
                    System.out.println("    You must include what needs to be done!");
                }
            } else if (cWord.equals("deadline")) {
                try {
                    deadlineCommand(task);
                } catch (DukeException e){
                    System.out.println("    You must specify when the deadline is by!");
                }
            } else if (cWord.equals("event")) {
                try {
                    eventCommand(task);
                } catch (DukeException e){
                    System.out.println("    You must specify when the event is at!");
                }
            } else {
                System.out.println("    I'm sorry! I don't know what that means :( ");
            }
            System.out.println(BAR);
            command = sc.nextLine();
        }
        goodbye();
    }

    /**
     * Goodbye text
     */
    private static void goodbye() {
        System.out.println(BAR);
        System.out.println("    Bye! See ya next time :)");
        System.out.println(BAR);
    }

    /**
     * The introduction command, which generates the introduction script
     */
    private static void introduction() {
        taskList = new types.Task[MAX_TASKS];
        numTasks = 0;
        System.out.println(BAR);
        System.out.println("    Hey! I'm Jamun");
        System.out.println("    How can I help you?");
        System.out.println(BAR);
    }

    /**
     * The event command, which adds a new event and when it is
     * @param task what the event is and when it is at, denoted by /at
     */
    private static void eventCommand(String task) throws DukeException{
        if (!task.contains("/at")) {
            throw new DukeException();
        }
        String[] splitTask2 = task.split("/at");
        types.Task t = new types.Event(splitTask2[0], numTasks, splitTask2[1]);
        addTask(t);
        printTask(t);
        try {
            taskListWrite(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print the task
     * @param t task to be printed
     */
    private static void printTask(types.Task t) {
        System.out.println("    Got it. I've added this task: ");
        System.out.println("    " + t.toString());
        System.out.println("    Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * The deadline command, which adds a new task and its deadline
     * @param task what the deadline is for and when it needs to be done by, denoted by /by
     */
    private static void deadlineCommand(String task) throws DukeException {
        if (!task.contains("/by")) {
            throw new DukeException();
        }
        String[] splitTask2 = task.split("/by");
        types.Task t = new types.Deadline(splitTask2[0], numTasks, splitTask2[1]);
        addTask(t);
        printTask(t);
        try {
            taskListWrite(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add task to list and increment number of tasks
     * @param t task to be added to list
     */
    private static void addTask(types.Task t) {
        taskList[numTasks] = t;
        numTasks++;
    }

    /**
     * The to do command, which adds a new task to be done
     * @param task what needs to be done
     */
    private static void todoCommand(String task) throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException();
        }
        types.Task t = new types.Todo(task, numTasks);
        addTask(t);
        printTask(t);
        try {
            taskListWrite(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The done command, which can mark and event as done or not
     * @param command the command that was done and the number of the command
     */
    private static void doneCommand(String command) throws DukeException {
        String[] splitTask2 = command.split(" ");
        if (splitTask2.length != 2) {
            throw new DukeException();
        }
        int taskDoneNum = Integer.parseInt(splitTask2[1]);
        if (taskDoneNum - 1 >= numTasks || taskDoneNum <= 0) {
            System.out.println("    You haven't done that many tasks yet!");
        } else {
            taskList[taskDoneNum - 1].setDone(true);
            System.out.println("    Good work! I've marked this task as done!");
            System.out.println("    " + taskList[taskDoneNum - 1].toString());
        }
        try {
            taskListWrite(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The list command, which lists all tasks and statuses
     */
    private static void listCommand() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < numTasks; i++) {
            System.out.print("    " + (i + 1) + ".");
            System.out.println(taskList[i].toString());
        }
    }

    private static void writeToFile(types.Task t, FileWriter fr) throws IOException {
        String str = t.getType() + " | " + t.getDone() + " | " + t.getName();
        if (t.getType().equals("D")) {
            str += " | " + t.getBy();
        } else if (t.getType().equals("E")) {
            str += " | " + t.getAt();
        }
        fr.write(str);
        fr.write(System.lineSeparator());
    }

    private static void taskListWrite(types.Task[] arr) throws IOException {
        File f = new File(FILE_PATH);
        FileWriter fr = null;
        fr = new FileWriter(f);
        for (int i = 0; i < numTasks; i++) {
            writeToFile(arr[i], fr);
        }
        fr.close();
    }
}

