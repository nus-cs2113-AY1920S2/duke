package duke.parser;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.Ui;

import static duke.Duke.tasks;

public class Parser {
    
    private static final String COMMAND_LIST_WORD = "list";
    
    private static final String COMMAND_BLAH_WORD = "blah";
    
    public static final String COMMAND_BYE_WORD = "bye";
    
    private static final String COMMAND_DONE_WORD = "done";
    
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_EVENT_WORD = "event";
    
    private static final String COMMAND_DELETE_WORD = "delete";
    
    private static final String COMMAND_HELP_WORD = "help";
    
    private static final String MISSING_DATE_MESSAGE_EXCEPTION = "OOPS!!! The date of a %s cannot be empty.";
    private static final String MISSING_DESCRIPTION_MESSAGE_EXCEPTION =
            "OOPS!!! The description of a %s cannot be empty.";
    private static final String SPILT_BY_SPACE = "\\s+";
    private static final String SPILT_BY_SLASH = "/";
    
    /* Solution below adapted from
       https://github.com/nus-cs2113-AY1920S2/contacts/blob/master/src/main/java/Contacts1.java
     */
    public void executeCommand(String userInput) {
        String[] commandTypeAndParams = splitInputLine(userInput, SPILT_BY_SPACE);
        String[] paramAndDate = splitInputLine(commandTypeAndParams[1], SPILT_BY_SLASH);
        String commandWord = commandTypeAndParams[0].trim();
        String commandArgs = paramAndDate[0].trim();
        String commandDate = paramAndDate[1].trim();
        
        try {
            hasEmptyDescription(commandWord, commandArgs);
            hasEmptyDate(commandWord, commandDate);
            operateCommand(commandWord, commandArgs, commandDate);
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }
    
    private void operateCommand(String commandWord, String commandArgs, String commandDate) {
        switch (commandWord.toLowerCase()) {
        case COMMAND_TODO_WORD:
            tasks.add(new Todo(commandArgs));
            Ui.displayAddMessage();
            break;
        case COMMAND_DEADLINE_WORD:
            tasks.add(new Deadline(commandArgs, commandDate));
            Ui.displayAddMessage();
            break;
        case COMMAND_EVENT_WORD:
            tasks.add(new Event(commandArgs, commandDate));
            Ui.displayAddMessage();
            break;
        case COMMAND_LIST_WORD:
            Ui.displayTaskList();
            break;
        case COMMAND_DONE_WORD:
            Ui.displayDoneMessage(commandArgs);
            break;
        case COMMAND_BYE_WORD:
            break;
        case COMMAND_BLAH_WORD:
            Ui.displayBlahMessage();
            break;
        case COMMAND_DELETE_WORD:
            Ui.displayRemoveMessage(commandArgs);
            break;
        case COMMAND_HELP_WORD:
            Ui.getUsageInfoForAllCommands();
            break;
        default:
            Ui.displayInvalidMessage();
            break;
        }
    }
    
    private static void hasEmptyDescription(String commandWord, String commandArgs) throws DukeException {
        if ((commandWord.equalsIgnoreCase(COMMAND_TODO_WORD) || commandWord.equalsIgnoreCase(COMMAND_DEADLINE_WORD) ||
                commandWord.equalsIgnoreCase(COMMAND_EVENT_WORD)) && commandArgs.equals("")) {
            throw new DukeException(String.format(MISSING_DESCRIPTION_MESSAGE_EXCEPTION, commandWord));
            
        }
    }
    
    private static void hasEmptyDate(String commandWord, String commandDate) throws DukeException {
        if ((commandWord.equalsIgnoreCase(COMMAND_DEADLINE_WORD) || commandWord.equalsIgnoreCase(COMMAND_EVENT_WORD)) &&
                commandDate.equals("")) {
            throw new DukeException(String.format(MISSING_DATE_MESSAGE_EXCEPTION, commandWord));
        }
    }
    
    /* Solution below adapted from
       https://github.com/nus-cs2113-AY1920S2/contacts/blob/master/src/main/java/Contacts1.java
     */
    public static String[] splitInputLine(String rawUserInput, String regex) {
        String[] split = rawUserInput.trim().split(regex, 2);
        return split.length == 2 ? split : new String[]{split[0], ""}; // else no parameters
    }
}
