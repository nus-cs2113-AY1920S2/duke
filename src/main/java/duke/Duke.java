package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

import static duke.PrintMessage.displayWelcomeMessage;
import static duke.PrintMessage.displayAddMessage;
import static duke.PrintMessage.displayDoneMessage;
import static duke.PrintMessage.displayInvalidMessage;
import static duke.PrintMessage.displayBlahMessage;
import static duke.PrintMessage.displayByeMessageAndExit;
import static duke.PrintMessage.displayTaskList;
import static duke.PrintMessage.displayRemoveMessage;

import java.util.Scanner;

public class Duke {
    
    private static final String COMMAND_LIST_WORD = "list";
    
    private static final String COMMAND_BLAH_WORD = "blah";
    
    private static final String COMMAND_BYE_WORD = "bye";
    
    private static final String COMMAND_DONE_WORD = "done";
    
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_EVENT_WORD = "event";
    
    private static final String COMMAND_DELETE_WORD = "delete";
    
    private static final String ENTER_A_COMMAND = "Enter a command: ";
    private static final String COMMAND_ENTERED = "Command entered: ";
    
    private static final String MISSING_DATE_MESSAGE = "OOPS!!! The date of a %s cannot be empty.";
    private static final String MISSING_DESCRIPTION_MESSAGE = "OOPS!!! The description of a %s cannot be empty.";
    private static final String SPILT_BY_SPACE = "\\s+";
    private static final String SPILT_BY_SLASH = "/";
    
    private static Scanner in = new Scanner(System.in);
    
    protected static ArrayList<Task> tasks = new ArrayList<>();
    
    public static void main(String[] args) {
        displayWelcomeMessage();
        while (true) {
            String userInput = getUserInput();
            executeCommand(userInput);
        }
    }
    
    /* Solution below adapted from
       https://github.com/nus-cs2113-AY1920S2/contacts/blob/master/src/main/java/Contacts1.java
     */
    private static void executeCommand(String userInput) {
        String[] commandTypeAndParams = splitInputLine(userInput, SPILT_BY_SPACE);
        String[] paramAndDate = splitInputLine(commandTypeAndParams[1], SPILT_BY_SLASH);
        String commandWord = commandTypeAndParams[0];
        String commandArgs = paramAndDate[0];
        String commandDate = paramAndDate[1];
        
        try {
            hasEmptyDescription(commandWord, commandArgs);
            hasEmptyDate(commandWord, commandDate);
            operateCommand(commandWord, commandArgs, commandDate);
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }
    
    private static void operateCommand(String commandWord, String commandArgs, String commandDate) {
        switch (commandWord.toLowerCase()) {
        case COMMAND_TODO_WORD:
            tasks.add(new Todo(commandArgs));
            displayAddMessage();
            break;
        case COMMAND_DEADLINE_WORD:
            tasks.add(new Deadline(commandArgs, commandDate));
            displayAddMessage();
            break;
        case COMMAND_EVENT_WORD:
            tasks.add(new Event(commandArgs, commandDate));
            displayAddMessage();
            break;
        case COMMAND_LIST_WORD:
            displayTaskList();
            break;
        case COMMAND_DONE_WORD:
            displayDoneMessage(commandArgs);
            break;
        case COMMAND_BYE_WORD:
            displayByeMessageAndExit();
            break;
        case COMMAND_BLAH_WORD:
            displayBlahMessage();
            break;
        case COMMAND_DELETE_WORD:
            displayRemoveMessage(commandArgs);
            break;
        default:
            displayInvalidMessage();
            break;
        }
    }
    
    private static void hasEmptyDescription(String commandWord, String commandArgs) throws DukeException {
        if ((commandWord.equalsIgnoreCase(COMMAND_TODO_WORD) || commandWord.equalsIgnoreCase(COMMAND_DEADLINE_WORD) ||
                commandWord.equalsIgnoreCase(COMMAND_EVENT_WORD)) && commandArgs.equals("")) {
            throw new DukeException(String.format(MISSING_DESCRIPTION_MESSAGE, commandWord));
            
        }
    }
    
    private static void hasEmptyDate(String commandWord, String commandDate) throws DukeException {
        if ((commandWord.equalsIgnoreCase(COMMAND_DEADLINE_WORD) || commandWord.equalsIgnoreCase(COMMAND_EVENT_WORD)) &&
                commandDate.equals("")) {
            throw new DukeException(String.format(MISSING_DATE_MESSAGE, commandWord));
        }
    }
    
    private static String getUserInput() {
        System.out.print(ENTER_A_COMMAND);
        String inputLine = in.nextLine();
        echoUserInput(inputLine.trim());
        return inputLine.trim();
    }
    
    private static void echoUserInput(String userInput) {
        System.out.println(COMMAND_ENTERED + userInput);
    }
    
    /* Solution below adapted from
       https://github.com/nus-cs2113-AY1920S2/contacts/blob/master/src/main/java/Contacts1.java
     */
    public static String[] splitInputLine(String rawUserInput, String regex) {
        String[] split = rawUserInput.trim().split(regex, 2);
        return split.length == 2 ? split : new String[]{split[0], ""}; // else no parameters
    }
    
    
}
