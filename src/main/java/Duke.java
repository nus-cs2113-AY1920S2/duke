/**
 * CS2113T Semester 2 AY19/20
 * Individual Project
 * <p>
 * Project Duke is a educational software project designed to take you through
 * the steps of building a small software incrementally,
 * while applying as many Java and SE techniques as possible along the way.
 * <p>
 * The project aims to build a product named Duke, a Personal Assistant Chatbot that
 * helps a person to keep track of various things. The name Duke was chosen as a placeholder name,
 * in honor of Duke, the Java Mascot. You may give it any other name and personality you wish.
 *
 * @file/s: Duke.java Printer.java Storage.java Task.java
 * @author: Tan Zheng Fu Justin
 */

import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final int LIMIT = 2;
    public static final int LIST_INDEX = 1;
    public static final int TASK_DESCRIPTION = 1;
    public static final int TASK_DEADLINE = 0;
    public static final int DUKE_COMMAND = 0;

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

    public static void echoUntilBye() {
        boolean isBye = false;
        while (!isBye) {
            Task task = readFromUser();
            List<String> commands = Parser.parseCommand(task.description);
            String command = commands.get(DUKE_COMMAND);
            String description;
            String dateLine;

            switch (command) {
            case "bye":
                isBye = true;
                exits();
                continue;

            case "list":
                myTasks.displayTasks();
                continue;

            case "done":
                int index = Integer.parseInt(commands.get(LIST_INDEX));
                Task t = myTasks.getTask(index);
                if (Task.isValid(t)) {
                    t.markAsDone();
                    Printer.printConfirmationMessage(t);
                } else {
                    Printer.printError();
                }
                continue;

            case "todo":
                description = commands.get(TASK_DESCRIPTION);
                ToDo toDoTask = new ToDo(description);
                myTasks.storeTasks(toDoTask);
                Printer.printConfirmationMessage(toDoTask);
                continue;

            case "deadline":
                String descriptionAndDate = commands.get(TASK_DESCRIPTION); //change constant name here TODO
                List<String> separated = Parser.parseDescriptionAndDate(descriptionAndDate);
                description = separated.get(TASK_DESCRIPTION);
                dateLine = separated.get(TASK_DEADLINE);
                Deadline deadlineTask = new Deadline(description, dateLine);
                myTasks.storeTasks(deadlineTask);
                Printer.printConfirmationMessage(deadlineTask);
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
