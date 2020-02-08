/**
 *  CS2113T Semester 2 AY19/20
 *  Individual Project
 *
 *  Project Duke is a educational software project designed to take you through
 *      the steps of building a small software incrementally,
 *      while applying as many Java and SE techniques as possible along the way.
 *
 *  The project aims to build a product named Duke, a Personal Assistant Chatbot that
 *      helps a person to keep track of various things. The name Duke was chosen as a placeholder name,
 *      in honor of Duke, the Java Mascot. You may give it any other name and personality you wish.
 *
 *  @file/s: Duke.java Printer.java Storage.java Task.java
 *  @author: Tan Zheng Fu Justin
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final int LIMIT = 2;
    static Storage myTasks = new Storage();

    public static void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Printer.printStart(logo);
    }

    public static void exits() {
        Printer.printLines();
        Printer.printIndentation();
        Printer.printBye();
        Printer.printLines();
    }

    public static Task readFromUser() {
        Scanner in = new Scanner(System.in);
        String userInput;

        userInput = in.nextLine();
        Task task = new Task(userInput);

        return task;
    }

    public static List<String> parseCommand(String description) {
        List<String> commands = new ArrayList<>();
        commands = Arrays.asList(description.split(" ", LIMIT));
        return commands;
    }

    public static void echoUntilBye() {
        boolean isBye = false;
        while (!isBye) {
            Task task = readFromUser();
            List<String> commands = parseCommand(task.description);
            String command = commands.get(0);
            switch (command) {
            case "bye":
                isBye = true;
                exits();
                continue;
            case "list":
                myTasks.displayTasks();
                continue;
            case "done":
                try {
                    int index = Integer.parseInt(commands.get(1));
                    Task t = myTasks.getTask(index);
                    if (Task.isValid(t)) {
                        t.markAsDone();
                        Printer.printConfirmationMessage(t);
                    } else {
                        Printer.printError();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.printError();
                }
                continue;
            case "todo":
                String description = commands.get(1);
                ToDo toDoTask = new ToDo(description);
                myTasks.storeTasks(toDoTask);
                Printer.printConfirmationMessage(toDoTask);
                continue;

            default:
                myTasks.storeTasks(task);
                Printer.printConfirmationMessage(task.description);
            }
        }
    }

    public static void main(String[] args) {
        start();
        Printer.printLines();
        Printer.printGreetings();
        Printer.printLines();
        echoUntilBye();
    }
}
