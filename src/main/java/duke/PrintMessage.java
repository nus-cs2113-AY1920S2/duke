package duke;

import duke.exception.DukeException;

import java.io.IOException;

import static duke.Duke.tasks;
import static duke.Storage.appendToFile;
import static duke.Storage.modifyFileContent;
import static duke.Storage.deleteFileContent;


public class PrintMessage {
    
    private static final String DIVIDER = "____________________________________________________________";
    private static final String LS = System.lineSeparator();
    private static final String TAB = "\t";
    
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke" + LS + "What can i do for you?";
    
    private static final String COMMAND_LIST_EMPTY_MESSAGE = TAB + "The list is empty!";
    private static final String COMMAND_LIST_MESSAGE = "Here are the tasks in your list:";
    
    private static final String COMMAND_BLAH_MESSAGE = "I'm sorry, but I don't know what that means :-(";
    
    private static final String COMMAND_BYE_MESSAGE = TAB + "Bye. Hope to see you again soon!";
    
    private static final String COMMAND_DONE_MESSAGE = TAB + "Nice! I've marked this task as done:";
    
    private static final String COMMAND_ADD_MESSAGE =
            "Got it. I've added this task:" + LS + TAB + "%s" + LS + "Now " + "you have %d task(s) in the list.";
    
    private static final String COMMAND_DELETE_MESSAGE =
            "Noted. I've removed this task:" + LS + TAB + "%s" + LS + "Now " + "you have %d task(s) in the list.";
    
    private static final String COMMAND_INVALID_MESSAGE = "Invalid Command, please try another command" + LS +
            "type \"help\" in the command line console for instructions";
    private static final String COMMAND_INVALID_INDEX_MESSAGE = "Invalid Index";
    
    private static final String MESSAGE_COMMAND_HELP = "%s: %s";
    private static final String MESSAGE_COMMAND_HELP_EXAMPLE = TAB + "Example: %s";
    private static final String MESSAGE_COMMAND_HELP_PARAMETER = TAB + "Parameter(s): %s";
    
    private static final String COMMAND_HELP_WORD = "help";
    private static final String COMMAND_HELP_DESC = "Shows the program command line interface instructions";
    private static final String COMMAND_HELP_EXAMPLE = COMMAND_HELP_WORD;
    
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_LIST_DESC = "Displays all the tasks in the list with index numbers.";
    private static final String COMMAND_LIST_EXAMPLE = COMMAND_LIST_WORD;
    
    private static final String COMMAND_BYE_WORD = "bye";
    private static final String COMMAND_BYE_DESC = "Exits the program.";
    private static final String COMMAND_BYE_EXAMPLE = COMMAND_BYE_WORD;
    
    private static final String COMMAND_ADD_DESC = "Adds a task to the list.";
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_TODO_PARAMETER = "TASK";
    private static final String COMMAND_TODO_EXAMPLE = "todo read book";
    
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_DEADLINE_PARAMETER = "TASK /by DAY";
    private static final String COMMAND_DEADLINE_EXAMPLE = "deadline return book /by Sunday";
    
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_EVENT_PARAMETER = "TASK /by DAY_AND_TIME";
    private static final String COMMAND_EVENT_EXAMPLE = "event project meeting /at Mon 2-4pm";
    
    private static final String COMMAND_DONE_WORD = "done";
    private static final String COMMAND_DONE_DESC = "Marks the task done in the list.";
    private static final String COMMAND_DONE_PARAMETER = "NUMBER";
    private static final String COMMAND_DONE_EXAMPLE = COMMAND_DONE_WORD + " 1";
    
    private static final String COMMAND_DELETE_WORD = "delete";
    private static final String COMMAND_DELETE_DESC = "Deletes the task from the list.";
    private static final String COMMAND_DELETE_PARAMETER = "NUMBER";
    private static final String COMMAND_DELETE_EXAMPLE = COMMAND_DELETE_WORD + " 1";
    
    private static String getUsageInfoForHelp() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_HELP_WORD, COMMAND_HELP_DESC) + LS +
                String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_HELP_EXAMPLE) + LS;
    }
    
    private static String getUsageInfoForBye() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_BYE_WORD, COMMAND_BYE_DESC) + LS +
                String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_BYE_EXAMPLE);
    }
    
    private static String getUsageInfoForList() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_LIST_WORD, COMMAND_LIST_DESC) + LS +
                String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_LIST_EXAMPLE) + LS;
    }
    
    private static String getUsageInfoForDone() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_DONE_WORD, COMMAND_DONE_DESC) + LS +
                String.format(MESSAGE_COMMAND_HELP_PARAMETER, COMMAND_DONE_PARAMETER) + LS +
                String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DONE_EXAMPLE) + LS;
    }
    
    private static String getUsageInfoForDelete() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_DELETE_WORD, COMMAND_DELETE_DESC) + LS +
                String.format(MESSAGE_COMMAND_HELP_PARAMETER, COMMAND_DELETE_PARAMETER) + LS +
                String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DELETE_EXAMPLE) + LS;
    }
    
    private static String getUsageInfoForAdd() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_TODO_WORD, COMMAND_ADD_DESC) + LS +
                String.format(MESSAGE_COMMAND_HELP_PARAMETER, COMMAND_TODO_PARAMETER) + LS +
                String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_TODO_EXAMPLE) + LS + LS +
                String.format(MESSAGE_COMMAND_HELP, COMMAND_DEADLINE_WORD, COMMAND_ADD_DESC) + LS +
                String.format(MESSAGE_COMMAND_HELP_PARAMETER, COMMAND_DEADLINE_PARAMETER) + LS +
                String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DEADLINE_EXAMPLE) + LS + LS +
                String.format(MESSAGE_COMMAND_HELP, COMMAND_EVENT_WORD, COMMAND_ADD_DESC) + LS +
                String.format(MESSAGE_COMMAND_HELP_PARAMETER, COMMAND_EVENT_PARAMETER) + LS +
                String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_EVENT_EXAMPLE) + LS;
    }
    
    protected static void getUsageInfoForAllCommands() {
        printToConsole(DIVIDER, getUsageInfoForHelp(), getUsageInfoForList(), getUsageInfoForAdd(),
                getUsageInfoForDone(), getUsageInfoForDelete(), getUsageInfoForBye(), DIVIDER);
    }
    
    
    private static void printToConsole(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }
    
    protected static void displayWelcomeMessage() {
        printToConsole(DIVIDER, WELCOME_MESSAGE, DIVIDER);
    }
    
    protected static void displayAddMessage() {
        try {
            printToConsole(DIVIDER, String.format(COMMAND_ADD_MESSAGE, tasks.get(tasks.size() - 1), tasks.size()),
                    DIVIDER);
            appendToFile(tasks.get(tasks.size() - 1).toStorage());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    protected static void displayDoneMessage(String index) {
        try {
            if (index.equals("")) {
                throw new IOException();
            }
            int doneTaskIndex = Integer.parseInt(index) - 1;
            if (doneTaskIndex >= tasks.size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            tasks.get(doneTaskIndex).markAsDone();
            printToConsole(DIVIDER, COMMAND_DONE_MESSAGE, TAB + tasks.get(doneTaskIndex), DIVIDER);
            modifyFileContent(doneTaskIndex, tasks.get(doneTaskIndex).toStorage());
        } catch (ArrayIndexOutOfBoundsException e) {
            printToConsole(DIVIDER, COMMAND_INVALID_INDEX_MESSAGE, DIVIDER);
        } catch (IOException e) {
            printToConsole(DIVIDER, COMMAND_INVALID_INDEX_MESSAGE, DIVIDER);
        }
    }
    
    protected static void displayInvalidMessage() {
        try {
            throw new DukeException(COMMAND_INVALID_MESSAGE);
        } catch (DukeException e) {
            printToConsole(DIVIDER, COMMAND_INVALID_MESSAGE, DIVIDER);
        }
    }
    
    protected static void displayBlahMessage() {
        try {
            throw new DukeException(COMMAND_BLAH_MESSAGE);
        } catch (DukeException e) {
            printToConsole(e.toString());
        }
    }
    
    protected static void displayByeMessageAndExit() {
        printToConsole(DIVIDER, COMMAND_BYE_MESSAGE, DIVIDER);
        System.exit(0);
    }
    
    protected static void displayTaskList() {
        if (tasks.isEmpty()) {
            printToConsole(DIVIDER, COMMAND_LIST_EMPTY_MESSAGE, DIVIDER);
            return;
        }
        printToConsole(DIVIDER, COMMAND_LIST_MESSAGE);
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        printToConsole(DIVIDER);
    }
    
    protected static void displayRemoveMessage(String index) {
        try {
            int removeTaskIndex = Integer.parseInt(index) - 1;
            if (removeTaskIndex >= tasks.size() || removeTaskIndex < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            printToConsole(DIVIDER, String.format(COMMAND_DELETE_MESSAGE, tasks.get(removeTaskIndex), tasks.size() - 1),
                    DIVIDER);
            deleteFileContent(removeTaskIndex);
            tasks.remove(removeTaskIndex);
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            printToConsole(DIVIDER, COMMAND_INVALID_INDEX_MESSAGE, DIVIDER);
        }
    }
}
