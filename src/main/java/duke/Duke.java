package duke;

import duke.exception.FormatErrorException;
import duke.exception.IncompleteInputException;
import duke.exception.InvalidInputException;
import duke.exception.OutOfBoundsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {

    private static final int timingSpecifier = 4;

    private static final String welcomeMessage = "Hello! I'm KJ\nHow can I help you today?";

    private static final String completeMessage = "Nice! I've marked this task as done:";

    private static final String byeMessage = "Bye. Hope to see you again soon!";

    private static final String listMessage = "Here are the tasks in your list:";

    private static final String invalid = "Oops, I'm sorry but I don't know what that means :-(";

    private static final String incomplete = "Uh-oh, I need more information to process that request :-(";

    private static final String indexProblem = "It appears you have entered an invalid index :O";

    private static final String formatProblem = "The format of your command is incorrect, please use:\n"
            + "deadline (item) /by (time)\n" + "event (item) /at (time)";

    private static final int MAXIMUM_TASKS = 100;

    private static boolean shouldContinue;

    private static String command;

    private static String[] phrases;

    private static Scanner input;

    private static Task[] instructions;

    private static int instructionCount;

    public static void main(String[] args) {
        initDuke();
        displayWelcome();
        while (shouldContinue) {
            readInput();
            try {
                processCommand();
            } catch (InvalidInputException e) {
                printError(invalid);
            } catch (IncompleteInputException e) {
                printError(incomplete);
            } catch (NumberFormatException e) {
                printError(indexProblem);
            } catch (FormatErrorException e) {
                printError(formatProblem);
            } catch (OutOfBoundsException e) {
                printError(indexProblem);
            }

        }
    }

    private static void processCommand() throws InvalidInputException, IncompleteInputException, FormatErrorException,
            OutOfBoundsException {
        phrases = command.split(" ");
        switch(phrases[0]) {
        case "bye":
            sayBye();
            break;
        case "list":
            listTasks();
            break;
        case "done":
            checkCompleteInput();
            completeTask();
            break;
        case "deadline":
            checkCompleteInput();
            addDeadline();
            confirmTask();
            break;
        case "event":
            checkCompleteInput();
            addEvent();
            confirmTask();
            break;
        case "todo":
            checkCompleteInput();
            addTodo();
            confirmTask();
            break;
        default:
            throw new InvalidInputException();
        }
    }

    private static void printError(String s) {
        System.out.println(s);
    }

    private static void checkCompleteInput() throws IncompleteInputException {
        if (phrases.length == 1) {
            throw new IncompleteInputException();
        }
    }

    private static void addTodo() {
        String description = command.substring(phrases[0].length()+1);
        instructions[instructionCount] = new Todo(description);
        instructionCount += 1;
    }

    private static void addEvent() throws FormatErrorException {
        int index = command.indexOf("/at");
        if (index == -1) {
            throw new FormatErrorException();
        }
        String description = command.substring(phrases[0].length()+1, index - 1);
        String duration = command.substring(index + timingSpecifier);
        instructions[instructionCount] = new Event(description, duration);
        instructionCount += 1;
    }

    private static void addDeadline() throws FormatErrorException {
        int index = command.indexOf("/by");
        if (index == -1) {
            throw new FormatErrorException();
        }
        String description = command.substring(phrases[0].length()+1, index - 1);
        String by = command.substring(index + timingSpecifier);
        instructions[instructionCount] = new Deadline(description, by);
        instructionCount += 1;
    }
    private static void completeTask() throws OutOfBoundsException {
        int index = Integer.parseInt(phrases[1]);
        if (index > instructionCount) {
            throw new OutOfBoundsException();
        }
        instructions[index-1].markAsDone();
        System.out.println(completeMessage);
        System.out.println("  " + instructions[index-1]);
    }

    private static void listTasks() {
        System.out.println(listMessage);
        for (int i = 0; i < instructionCount; i++) {
            System.out.println((i+1) + "." + instructions[i]);
        }
    }

    private static void sayBye() {
        System.out.println(byeMessage);
        shouldContinue = false;
    }

    private static void readInput() {
        command = input.nextLine();
    }

    private static void initDuke() {
        shouldContinue = true;
        input = new Scanner(System.in);
        instructions = new Task[MAXIMUM_TASKS];
        instructionCount = 0;
    }

    private static void confirmTask() {
        System.out.println("Got it. I've added this task:\n  " + instructions[instructionCount-1] + "\n"
            + "Now you have " + instructionCount + " tasks in the list.");
    }

    private static void displayWelcome() {
        System.out.println(welcomeMessage);
    }
}
