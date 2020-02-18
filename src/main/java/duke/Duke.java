package duke;

import types.Deadline;
import types.Todo;

import java.io.File;
import java.io.FileNotFoundException;
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

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        introduction();
        File f = loadFile();
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
                    doneCommand(command, f);
                } catch (DukeException e){
                    System.out.println("    You must include the number of the completed task!");
                }
            } else if (cWord.equals("todo")) {
                try {
                    todoCommand(task, f);
                } catch (DukeException e){
                    System.out.println("    You must include what needs to be done!");
                }
            } else if (cWord.equals("deadline")) {
                try {
                    deadlineCommand(task, f);
                } catch (DukeException e){
                    System.out.println("    You must specify when the deadline is by!");
                }
            } else if (cWord.equals("event")) {
                try {
                    eventCommand(task, f);
                } catch (DukeException e){
                    System.out.println("    You must specify when the event is at!");
                }
            } else if (cWord.equals("delete")) {
               try {
                   deleteCommand(command, f);
               } catch (DukeException e) {
                   System.out.println("    You must include the number of deleted task!");
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
    private static void eventCommand(String task, File f) throws DukeException{
        if (!task.contains("/at")) {
            throw new DukeException();
        }
        String[] splitTask2 = task.split("/at");
        types.Task t = new types.Event(splitTask2[0], splitTask2[1]);
        addTask(t);
        printTask(t);
        try {
            taskListWrite(taskList, f);
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
    private static void deadlineCommand(String task, File f) throws DukeException {
        if (!task.contains("/by")) {
            throw new DukeException();
        }
        String[] splitTask2 = task.split("/by");
        types.Task t = new types.Deadline(splitTask2[0], splitTask2[1]);
        addTask(t);
        printTask(t);
        try {
            taskListWrite(taskList, f);
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
    private static void todoCommand(String task, File f) throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException();
        }
        types.Task t = new types.Todo(task);
        addTask(t);
        printTask(t);
        try {
            taskListWrite(taskList, f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The done command, which can mark and event as done or not
     * @param command the command that was done and the number of the command
     */
    private static void doneCommand(String command, File f) throws DukeException {
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
            taskListWrite(taskList, f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The delete command, which deletes an item from the list
     * @param command number of task to be deleted
     * @throws DukeException an error
     */
    private static void deleteCommand(String command, File f) throws DukeException {
        String[] splitTask2 = command.split(" ");
        if (splitTask2.length != 2) {
            throw new DukeException();
        }
        int taskDeleteNum = Integer.parseInt(splitTask2[1]);
        if (taskDeleteNum - 1 >= numTasks || taskDeleteNum <= 0) {
            System.out.println("    You haven't done that many tasks yet!");
        } else {
            System.out.println("    Noted. I've removed this task: ");
            System.out.println("    " + taskList[taskDeleteNum - 1].toString());
            taskList = deleteIndex(taskList, taskDeleteNum - 1);
            System.out.println("    Now you have " + numTasks + " tasks in the list.");
        }
        try {
            taskListWrite(taskList, f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes an item from the task array
     * @param arr original array
     * @param index of item to be deleted
     * @return the new array
     */
    private static types.Task[] deleteIndex(types.Task[] arr, int index) {
        types.Task[] result = new types.Task[arr.length - 1];
        for (int i = 0; i < result.length; i++) {
            if (i < index) {
                result[i] = arr[i];
            } else {
                result[i] = arr[i+1];
            }
        }
        numTasks--;
        return result;
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

    /**
     * Writes a task to file
     * @param t task
     * @param fr fileWriters
     * @throws IOException exception
     */
    private static void writeToFile(types.Task t, FileWriter fr) throws IOException {
        String str = t.getType() + " ; " + t.getDone() + " ; " + t.getName();
        if (t.getType().equals("D")) {
            str += " ; " + t.getBy();
        } else if (t.getType().equals("E")) {
            str += " ; " + t.getAt();
        }
        fr.write(str);
        fr.write(System.lineSeparator());
    }

    /**
     * Writes entire task list array to file
     * @param arr task list array
     * @throws IOException exception
     */
    private static void taskListWrite(types.Task[] arr, File f) throws IOException {
        FileWriter fr = null;
        fr = new FileWriter(f);
        for (int i = 0; i < numTasks; i++) {
            writeToFile(arr[i], fr);
        }
        fr.close();
    }

    private static File loadFile() throws FileNotFoundException {
        File f = new File(FILE_PATH);
        if (f.length() != 0) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                boolean bool = false;
                String cl = s.nextLine();
                String[] splitTask = cl.split(" ; ");
                String type = splitTask[0];
                if (type.equals("D")) {
                    taskList[numTasks] = new types.Deadline(splitTask[2], splitTask[3]);
                    if (Integer.parseInt(splitTask[1].trim()) == 1) {
                        bool = true;
                    }
                    taskList[numTasks].setDone(bool);
                    numTasks++;
                } else if (type.equals("E")) {
                    taskList[numTasks] = new types.Event(splitTask[2], splitTask[3]);
                    if (Integer.parseInt(splitTask[1]) == 1) {
                        bool = true;
                    }
                    taskList[numTasks].setDone(bool);
                    numTasks++;
                } else {
                    taskList[numTasks] = new types.Todo(splitTask[2]);
                    if (Integer.parseInt(splitTask[1]) == 1) {
                        bool = true;
                    }
                    taskList[numTasks].setDone(bool);
                    numTasks++;
                }
            }
        }
        return f;
    }
}

