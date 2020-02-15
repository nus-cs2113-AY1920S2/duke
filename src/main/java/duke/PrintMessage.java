package duke;

import duke.exception.DukeException;

import static duke.Duke.tasks;

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
    
    private static final String COMMAND_INVALID_MESSAGE = "Command is invalid, " + "please try another command";
    private static final String COMMAND_INVALID_INDEX_MESSAGE = "Index is invalid";
    
    private static void printToConsole(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }
    
    protected static void displayWelcomeMessage() {
        printToConsole(DIVIDER, WELCOME_MESSAGE, DIVIDER);
    }
    
    protected static void displayAddMessage() {
        printToConsole(DIVIDER, String.format(COMMAND_ADD_MESSAGE, tasks.get(tasks.size() - 1), tasks.size()), DIVIDER);
    }
    
    protected static void displayDoneMessage(String index) {
        try {
            int doneTaskIndex = Integer.parseInt(index) - 1;
            if (doneTaskIndex >= tasks.size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            tasks.get(doneTaskIndex).markAsDone();
            printToConsole(DIVIDER, COMMAND_DONE_MESSAGE, TAB + tasks.get(doneTaskIndex), DIVIDER);
        } catch (ArrayIndexOutOfBoundsException e) {
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
            tasks.remove(removeTaskIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            printToConsole(DIVIDER, COMMAND_INVALID_INDEX_MESSAGE, DIVIDER);
        }
    }
}
