package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import static duke.PrintMessage.displayWelcomeMessage;
import static duke.PrintMessage.displayAddMessage;
import static duke.PrintMessage.displayDoneMessage;
import static duke.PrintMessage.displayInvalidMessage;
import static duke.PrintMessage.displayBlahMessage;
import static duke.PrintMessage.displayByeMessageAndExit;
import static duke.PrintMessage.displayTaskList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    
    private static final String COMMAND_LIST_WORD = "list";
    
    private static final String COMMAND_BLAH_WORD = "blah";
    
    private static final String COMMAND_BYE_WORD = "bye";
    
    private static final String COMMAND_DONE_WORD = "done";
    
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_EVENT_WORD = "event";
    
    private static final String ENTER_A_COMMAND = "Enter a command: ";
    private static final String COMMAND_ENTERED = "Command entered: ";
    
    private static final String MISSING_DATE_MESSAGE = "OOPS!!! The date of a %s cannot be empty.";
    private static final String MISSING_DESCRIPTION_MESSAGE = "OOPS!!! The description of a %s cannot be empty.";
    private static final String SPILT_BY_SPACE = "\\s+";
    private static final String SPILT_BY_SLASH = "/";
    
    private static final String FILEPATH = "\\data\\duke.txt";
    
    private static Scanner in = new Scanner(System.in);
    
    
    private static final int MAX_CAPACITY = 100;
    protected static Task[] tasks = new Task[MAX_CAPACITY];
    
    public static void main(String[] args) {
        final String filePath = getRelativePath().replace("\\", "/");
        new Storage(filePath);
        displayWelcomeMessage();
        while (true) {
            String userInput = getUserInput();
            executeCommand(filePath, userInput);
        }
    }
    
    //@@author geoO-reused
    //Reused from https://stackoverflow.com/questions/4871051/getting-the-current-working-directory-in-java
    public static String getRelativePath() {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        return s + FILEPATH;
    }
    //@@author geoO-reused
    
    /* Solution below adapted from
       https://github.com/nus-cs2113-AY1920S2/contacts/blob/master/src/main/java/Contacts1.java
     */
    private static void executeCommand(String filePath, String userInput) {
        String[] commandTypeAndParams = splitInputLine(userInput, SPILT_BY_SPACE);
        String[] paramAndDate = splitInputLine(commandTypeAndParams[1], SPILT_BY_SLASH);
        String commandWord = commandTypeAndParams[0].trim();
        String commandArgs = paramAndDate[0].trim();
        String commandDate = paramAndDate[1].trim();
        
        try {
            hasEmptyDescription(commandWord, commandArgs);
            hasEmptyDate(commandWord, commandDate);
            operateCommand(filePath, commandWord, commandArgs, commandDate);
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }
    
    private static void operateCommand(String filePath, String commandWord, String commandArgs, String commandDate) {
        switch (commandWord.toLowerCase()) {
        case COMMAND_TODO_WORD:
            tasks[Task.getTaskCount()] = new Todo(commandArgs);
            displayAddMessage(filePath);
            break;
        case COMMAND_DEADLINE_WORD:
            tasks[Task.getTaskCount()] = new Deadline(commandArgs, commandDate);
            displayAddMessage(filePath);
            break;
        case COMMAND_EVENT_WORD:
            tasks[Task.getTaskCount()] = new Event(commandArgs, commandDate);
            displayAddMessage(filePath);
            break;
        case COMMAND_LIST_WORD:
            displayTaskList();
            break;
        case COMMAND_DONE_WORD:
            displayDoneMessage(filePath, commandArgs);
            break;
        case COMMAND_BYE_WORD:
            displayByeMessageAndExit();
            break;
        case COMMAND_BLAH_WORD:
            displayBlahMessage();
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
