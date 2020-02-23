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
 * @author: Tan Zheng Fu Justin
 */

import java.io.File;
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

        System.exit(0);
    }

    public static Task readFromUser() {
        Scanner in = new Scanner(System.in);

        String userInput = in.nextLine();

        return new Task(userInput);
    }

    public static void echoUntilBye(File saveFile) {
        boolean isBye = false;
        while (!isBye) {
            Task task = readFromUser();
            List<String> commands = Parser.parseCommand(task.description);
            String command = commands.get(DUKE_COMMAND);

            int index;
            String indexAsString;

            String descriptionAndDeadline;
            String descriptionAndEventAt;

            List<String> separated;

            String description;
            String deadline;
            String eventAt;

            switch (command) {
            case "bye":
                isBye = true;
                exits();

            case "list":
                myTasks.displayTasks();
                continue;

            case "done":

                try {
                    indexAsString = commands.get(LIST_INDEX);
                    indexAsString = indexAsString.trim();

                    index = Integer.parseInt(indexAsString);
                    Task taskDone = myTasks.getTask(index);
                    taskDone.markAsDone(); //TODO QUESTION: How come.. this works?.. is it because its static?

                    FileSaver.updateFile(saveFile, index);

                    Printer.printConfirmationMessage(command, taskDone);

                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    Printer.printError(); //TODO add custom error message when accessing OFB index
                }
                continue;

            case "delete":
                try {
                    indexAsString = commands.get(LIST_INDEX);
                    indexAsString = indexAsString.trim();

                    index = Integer.parseInt(indexAsString);
                    Task taskDelete = myTasks.getTask(index);
                    myTasks.deleteTask(index);

                    Printer.printConfirmationMessage(command, taskDelete);

                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    Printer.printError(); //TODO add custom error message when accessing OFB index
                }

                continue;

            case "todo":
                try {
                    description = commands.get(TASK_DESCRIPTION);
                    description = description.trim();

                    DukeExceptionHandler.isBlank(description);

                    ToDo toDoTask = new ToDo(description);
                    myTasks.storeTasks(toDoTask);

                    FileSaver.saveFile(saveFile, description);

                    Printer.printConfirmationMessage(command, toDoTask);

                } catch (IndexOutOfBoundsException | BlankStringException e) {
                    Printer.printEmptyDescriptionError(command);
                    Printer.printHint(command);
                }
                continue;

            case "deadline":
                try {
                    descriptionAndDeadline = commands.get(TASK_DESCRIPTION_AND_DATE);
                    separated = Parser.parseDeadlineDate(descriptionAndDeadline);

                    description = separated.get(TASK_DESCRIPTION);
                    deadline = separated.get(TASK_DEADLINE);
                    description = description.trim();
                    deadline = deadline.trim();

                    DukeExceptionHandler.isBlank(description);
                    DukeExceptionHandler.isBlank(deadline);

                    Deadline deadlineTask = new Deadline(description, deadline);
                    myTasks.storeTasks(deadlineTask);

                    FileSaver.saveFile(command, saveFile, description, deadline);

                    Printer.printConfirmationMessage(command, deadlineTask);

                } catch (IndexOutOfBoundsException | BlankStringException e) {
                    Printer.printFormatError(command);
                    Printer.printHint(command);
                }
                continue;

            case "event":
                try {
                    descriptionAndEventAt = commands.get(TASK_DESCRIPTION_AND_DATE);
                    separated = Parser.parseEventAt(descriptionAndEventAt);

                    description = separated.get(TASK_DESCRIPTION);
                    eventAt = separated.get(TASK_EVENT_AT);
                    description = description.trim();
                    eventAt = eventAt.trim();

                    DukeExceptionHandler.isBlank(description);
                    DukeExceptionHandler.isBlank(eventAt);

                    Event eventTask = new Event(description, eventAt);
                    myTasks.storeTasks(eventTask);

                    FileSaver.saveFile(command, saveFile, description, eventAt);

                    Printer.printConfirmationMessage(command, eventTask);

                } catch (IndexOutOfBoundsException | BlankStringException e) {
                    Printer.printFormatError(command);
                    Printer.printHint(command);
                }
                continue;

            default:
                Printer.printUnknownCommandError(command);
            }
        }
    }

    public static void main(String[] args) {
        start();

        File saveFile = FileSaver.makeNewSaveFile();
        FileLoader.loadFile(myTasks, saveFile);

        Printer.printLines();
        Printer.printGreetings();
        Printer.printLines();
        echoUntilBye(saveFile);
    }
}
