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
    public static final int TASK_DESCRIPTION_AND_DATE = 1;
    public static final int TASK_DEADLINE = 0;
    public static final int TASK_EVENT_AT = 0;
    public static final int DUKE_COMMAND = 0;
//ghjghjg
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
            String descriptionAndDate;
            List<String> separated;

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
                try {
                    Task t = myTasks.getTask(index);
                    t.markAsDone();
                    Printer.printConfirmationMessage(t);

                } catch (IndexOutOfBoundsException e) {
                    Printer.printError(); //TODO add custom error message when accessing OFB index
                }
                continue;

            case "todo":
                try {
                    description = commands.get(TASK_DESCRIPTION);
                    DukeExceptionHandler.isBlank(description);
                    ToDo toDoTask = new ToDo(description);
                    myTasks.storeTasks(toDoTask);
                    Printer.printConfirmationMessage(toDoTask);

                } catch (IndexOutOfBoundsException | BlankStringException e) {
                    Printer.printError(); //TODO add custom error message empty string
                }
                continue;

            case "deadline":
                try {
                    descriptionAndDate = commands.get(TASK_DESCRIPTION_AND_DATE);
                    separated = Parser.parseDeadlineDate(descriptionAndDate);

                    description = separated.get(TASK_DESCRIPTION);
                    dateLine = separated.get(TASK_DEADLINE);
                    description = description.trim();
                    dateLine = dateLine.trim();

                    DukeExceptionHandler.isBlank(description);
                    DukeExceptionHandler.isBlank(dateLine);

                    Deadline deadlineTask = new Deadline(description, dateLine);
                    myTasks.storeTasks(deadlineTask);

                    Printer.printConfirmationMessage(deadlineTask);

                } catch (IndexOutOfBoundsException | BlankStringException e) {
                    Printer.printError(); //TODO add custom error message empty string
                }
                continue;

            case "event":
                descriptionAndDate = commands.get(TASK_DESCRIPTION_AND_DATE);
                separated = Parser.parseEventAt(descriptionAndDate);
                description = separated.get(TASK_DESCRIPTION);
                dateLine = separated.get(TASK_EVENT_AT);
                Event eventTask = new Event(description, dateLine);
                myTasks.storeTasks(eventTask);
                Printer.printConfirmationMessage(eventTask);
                continue;

            default:
                Printer.printError();
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
