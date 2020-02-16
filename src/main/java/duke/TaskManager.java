package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    public static final String FORMAT_LINE = "------------------------------------";
    public static final String LISTING = "Here is the list of all tasks you have:";
    public static final String DONE_TASK = "Good job! You have done this task:";
    public static final String ADD_TASK = "Got it! I have added this task into your list:";
    public static final String DELETE_TASK= "Noted! I have deleted this task from the list:";
    private ArrayList<Task> tasks = new ArrayList<>();

    public void printCommand(String command, String str) {
        System.out.println(FORMAT_LINE);
        System.out.println(command);
        System.out.println("\t" + str);
        System.out.println("Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list");
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
            String[] meta = record.split("\\|");
            switch (meta[0].trim()) {
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
        fw.close();
    }

    public void addTodo(String command) {
        tasks.add(new Todo(command));
        printCommand(ADD_TASK, tasks.get(tasks.size()-1).print());
    }

    public void addDeadline(String command) {
        String[] commands = command.split("/", 2);
        tasks.add(new Deadline(commands));
        printCommand(ADD_TASK, tasks.get(tasks.size()-1).print());
    }

    public void addEvent(String command) {
        String[] commands = command.split("/", 2);
        tasks.add(new Event(commands));
        printCommand(ADD_TASK, tasks.get(tasks.size()-1).print());
    }

    public void listTask() {
        System.out.println(FORMAT_LINE);
        System.out.println(LISTING);
        int i = 1;
        for(Task task: tasks) {
            System.out.println(i++ + "." + task.print());
        }
        System.out.println(FORMAT_LINE);
        System.out.println();
    }

    public void markTask(int taskNo) {
        printCommand(DONE_TASK,"  [\u2713] " + tasks.get(taskNo-1).getName());
        tasks.get(taskNo-1).changeStatus(true);
    }

    public void delete(int taskNo) {
        String description = tasks.get(taskNo-1).print();
        tasks.remove(taskNo-1);
        printCommand(DELETE_TASK, description);
    }
}
