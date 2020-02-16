package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class TaskManager {
    public static final String FORMAT_LINE = "------------------------------------";
    public static final String LISTING = "Here is the list of all tasks you have:";
    public static final String DONE_TASK = "Good job! You have done this task:";
    public static final String ADD_TASK = "Got it! I have added this task into your list:";
    public static final String DELETE_TASK= "Noted! I have deleted this task from the list:";
    private ArrayList<Task> tasks = new ArrayList<>();
    private int numOfTasks = 0;

    public void printCommand(String command, String str) {
        System.out.println(FORMAT_LINE);
        System.out.println(command);
        System.out.println("\t" + str);
        System.out.println("Now you have " + numOfTasks + (numOfTasks > 1 ? " tasks" : " task") + " in the list");
        System.out.println("Remember to finish your tasks on time!");
        System.out.println(FORMAT_LINE);
        System.out.println();
    }

    public void loadFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner fileIn = new Scanner(f);
        String record;
        while (fileIn.hasNextLine()) {
            record = fileIn.nextLine();
            String[] meta = record.split("|");
            switch (meta[0]) {
            case "T":
                tasks.add(new Todo(meta[2].trim(), meta[1].trim().equals("1")));
                break;
            case "D":
                tasks.add(new Deadline(meta[2].trim(), meta[1].trim().equals("1"), meta[3].trim()));
                break;
            case "E":
                tasks.add(new Event(meta[2].trim(), meta[1].trim().equals("1"), meta[3].trim()));
                break;
            }
        }

    }

    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(Task task: tasks) {
            fw.write(task.textToFile());
        }
    }

    public void addTodo(String command) {
        tasks.add(new Todo(command));
        printCommand(ADD_TASK, tasks.get(numOfTasks++).print());
    }

    public void addDeadline(String command) {
        String[] commands = command.split("/", 2);
        tasks.add(new Deadline(commands));
        printCommand(ADD_TASK, tasks.get(numOfTasks++).print());
    }

    public void addEvent(String command) {
        String[] commands = command.split("/", 2);
        tasks.add(new Event(commands));
        printCommand(ADD_TASK, tasks.get(numOfTasks++).print());
    }

    public void listTask() {
        System.out.println(FORMAT_LINE);
        System.out.println(LISTING);
        for(int i = 0; i < numOfTasks; i++) {
            System.out.println(i+1 + "." + tasks.get(i).print());
        }
        System.out.println(FORMAT_LINE);
        System.out.println();
    }

    public void markTask(int taskNo) {
        printCommand(DONE_TASK,"  [✓] " + tasks.get(taskNo-1).getName());
        tasks.get(taskNo-1).changeStatus(true);
    }

    public void delete(int taskNo) {
        numOfTasks--;
        printCommand(DELETE_TASK, tasks.get(taskNo-1).print());
        tasks.remove(taskNo-1);
    }
}
