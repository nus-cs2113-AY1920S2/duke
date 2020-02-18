package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    public static final int CAPACITY = 100;
    public static final String BORDER = "____________________________________________________________";
    public static final int TODO = 5;
    public static final int DEADLINE = 9;
    public static final int EVENT = 6;
    public static final int DONE = 5;
    public static final int DATE = 4;
    public static final int DELETE = 7;
    public static final String PATH = "duke.txt";
    public static int taskCounter = 0;

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static void copyList(ArrayList<Task> tasks, int taskCounter) throws IOException {
        File f = new File(PATH);
        if (!f.exists()) {
            f.createNewFile();
        }
        String status;
        writeToFile(PATH, "");
        for (int i = 0; i < taskCounter; i++) {
            String line = tasks.get(i).toString();
            String[] sentence = line.split("]");
            String taskType = sentence[0].substring(1).toLowerCase();
            if (tasks.get(i).isDone) {
                status = "0";
            } else {
                status = "1";
            }

            if (taskType.equals("t")) {
                appendToFile(PATH, (taskType + " | " + status + " | " + tasks.get(i).description + System.lineSeparator()));
            } else {
                String [] newSentence = line.split(":");
                String dueDate = newSentence[1].substring(1);
                int length = dueDate.length();
                String newDate = newSentence[1].substring(1,length);
                appendToFile(PATH, (taskType + " | " + status + " | " + tasks.get(i).description + "| " + newDate + System.lineSeparator()));
            }
        }
    }

    private static void insertFileContents(ArrayList<Task> tasks) throws IOException {
        File f = new File(PATH); // create a File for the given file path
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String line;
            line = s.nextLine();
            String[] sentence = line.split("\\|");
            String taskType = sentence[0].toLowerCase();
            switch(taskType) {
            case "t ":
                Task t = new Todo(sentence[2]);
                tasks.add(t);
                taskCounter++;
                copyList(tasks, taskCounter);
                break;
            case "d ":
                Task d = new Deadline(sentence[2], sentence[3]);
                tasks.add(d);
                taskCounter++;
                copyList(tasks, taskCounter);
                break;
            case "e ":
                Task e = new Event(sentence[2], sentence[3]);
                tasks.add(e);
                taskCounter++;
                copyList(tasks, taskCounter);
                break;
            }
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            insertFileContents(tasks);
        } catch (IOException e) {
            System.out.println("Input/Output Error!");
        }

        while(true) {
            String line;
            line = input.nextLine();
            String[] sentence = line.split(" ");
            String taskType = sentence[0].toLowerCase();
            if (taskType.equalsIgnoreCase("bye")) {
                printByeMessage();
                break;
            }
            try {
                switch(taskType) {
                case "list":
                    printList(tasks, taskCounter);
                    break;
                case "todo":
                    line = line.substring(TODO);
                    Task t = new Todo(line);
                    tasks.add(t);
                    printAcknowledgement(tasks.get(taskCounter), taskCounter);
                    taskCounter++;
                    copyList(tasks, taskCounter);
                    break;
                case "deadline":
                    String[] deadlineWords = line.split("/");
                    String deadlineDescription = deadlineWords[0].substring(DEADLINE);
                    String by = deadlineWords[1].substring(DATE);
                    Task d = new Deadline(deadlineDescription, by);
                    tasks.add(d);
                    printAcknowledgement(tasks.get(taskCounter), taskCounter);
                    taskCounter++;
                    copyList(tasks, taskCounter);
                    break;
                case "event":
                    String[] eventWords = line.split("/");
                    String eventDescription = eventWords[0].substring(EVENT);
                    String at = eventWords[1].substring(DATE);
                    Task e = new Event(eventDescription, at);
                    tasks.add(e);
                    printAcknowledgement(tasks.get(taskCounter), taskCounter);
                    taskCounter++;
                    copyList(tasks, taskCounter);
                    break;
                case "done":
                    String doneNumber = line.substring(DONE);
                    int doneTaskNum = Integer.parseInt(doneNumber);
                    tasks.get(doneTaskNum - 1).markAsDone();
                    System.out.println(BORDER);
                    System.out.println("Nice! I've marked this task as done: " + tasks.get(doneTaskNum - 1).description);
                    System.out.println(BORDER);
                    copyList(tasks, taskCounter);
                    break;
                case "delete":
                    String deleteNumber = line.substring(DELETE);
                    int delTaskNum = Integer.parseInt(deleteNumber);
                    String delTaskName = tasks.get(delTaskNum -1).description;
                    tasks.remove(delTaskNum - 1);
                    System.out.println(BORDER);
                    System.out.println("Noted! I've removed this task:  " + delTaskName);
                    System.out.println("Now you have " + (tasks.size()) + " tasks in your list!");
                    System.out.println(BORDER);
                    taskCounter--;
                    copyList(tasks,taskCounter);
                    break;
                default:
                    throw new DukeException();
                }

            } catch (DukeException e) {
                System.out.println(BORDER);
                System.out.println("☹ OH NO!!! I'm sorry, but I don't know what that means! :o(");
                System.out.println(BORDER);
            } catch (NullPointerException e) {
                System.out.println(BORDER);
                System.out.println("☹ OH NO!!! There is no such task to be done! :o(");
                System.out.println(BORDER);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(BORDER);
                System.out.println("☹ OH NO!!! The description of a " + taskType + " cannot be empty! :o(");
                System.out.println(BORDER);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(BORDER);
                System.out.println("☹ OH NO!!! The description of a " + taskType + " cannot be empty! :o(");
                System.out.println(BORDER);
            } catch (IOException e) {
                System.out.println("Input/Output Error!");
            }
        }
    }

    private static void printList(ArrayList<Task> tasks, int taskCounter) {
        System.out.println(BORDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++){
            System.out.println(i+1 + ". " + tasks.get(i).toString());
        }
        System.out.println(BORDER);
    }

    private static void printAcknowledgement(Task task, int counter) {
        System.out.println(BORDER);
        System.out.println("Got it! I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + (counter + 1) + " tasks in your list!");
        System.out.println(BORDER);
    }

    private static void printByeMessage() {
        System.out.println(BORDER);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(BORDER);
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(BORDER);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        System.out.println(BORDER);
    }
}
