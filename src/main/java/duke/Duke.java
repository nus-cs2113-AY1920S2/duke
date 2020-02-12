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
import java.util.ArrayList;

public class Duke {

    private static final int timingSpecifier = 4;

    private static final String welcomeMessage = "Hello! I'm KJ\nHow can I help you today?";

    private static final String completeMessage = "Nice! I've marked this task as done:";

    private static final String deleteMessage = "Noted. I've removed this task:";

    private static final String byeMessage = "Bye. Hope to see you again soon!";

    private static final String listMessage = "Here are the tasks in your list:";

    private static final String invalid = "Oops, I'm sorry but I don't know what that means :-(";

    private static final String incomplete = "Uh-oh, I need more information to process that request :-(";

    private static final String indexProblem = "It appears you have entered an invalid index :O";

    private static final String formatProblem = "The format of your command is incorrect, please use:\n"
            + "deadline (item) /by (time)\n" + "event (item) /at (time)";

    private static String spacing = "  ";

    private static boolean shouldContinue;

    private static String command;

    private static String[] phrases;

    private static Scanner input;

    private static ArrayList<Task> instructions;

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
            completeTask("complete");
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
        case "delete":
            checkCompleteInput();
            completeTask("delete");
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
        instructions.add(new Todo(description));
    }

    private static void addEvent() throws FormatErrorException {
        int index = command.indexOf("/at");
        if (index == -1) {
            throw new FormatErrorException();
        }
        String description = command.substring(phrases[0].length()+1, index - 1);
        String duration = command.substring(index + timingSpecifier);
        instructions.add(new Event(description, duration));
    }

    private static void addDeadline() throws FormatErrorException {
        int index = command.indexOf("/by");
        if (index == -1) {
            throw new FormatErrorException();
        }
        String description = command.substring(phrases[0].length()+1, index - 1);
        String by = command.substring(index + timingSpecifier);
        instructions.add(new Deadline(description, by));
    }
    private static void completeTask(String type) throws OutOfBoundsException {
        int index = Integer.parseInt(phrases[1]);
        if (index > instructions.size()) {
            throw new OutOfBoundsException();
        }
        if (type.equals("complete")) {
            instructions.get(index-1).markAsDone();
            System.out.println(completeMessage);
            System.out.println(spacing + instructions.get(index-1));
        } else {
            Task obsolete = instructions.get(index-1);
            System.out.println(deleteMessage);
            System.out.println(spacing + obsolete);
            System.out.println("Now you have " + (instructions.size()-1) + " tasks in the list.");
            instructions.remove(obsolete);
        }

    }

    private static void listTasks() {
        System.out.println(listMessage);
        for (int i = 0; i < instructions.size(); i++) {
            System.out.println((i+1) + "." + instructions.get(i));
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
        instructions = new ArrayList<>();
    }

    private static void confirmTask() {
        System.out.println("Got it. I've added this task:\n  " + instructions.get(instructions.size()-1) + "\n"
            + "Now you have " + instructions.size() + " tasks in the list.");
    }

    private static void displayWelcome() {
        System.out.println(welcomeMessage);
    }
}
