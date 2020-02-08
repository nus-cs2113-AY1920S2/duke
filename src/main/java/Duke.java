import java.util.Scanner;

public class Duke {
    
    private static final String DIVIDER = "____________________________________________________________";
    private static final String LS = System.lineSeparator();
    private static final String TAB = "\t";
    private static final String EMPTY_STRING = "";
    
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke" + LS + "What can i do for you?";
    
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_LIST_EMPTY = TAB + "The list is empty!";
    private static final String COMMAND_LIST_MESSAGE = "Here are the tasks in your list:";
    
    private static final String COMMAND_BLAH_WORD = "blah";
    private static final String COMMAND_BLAH_MESSAGE = "I'm sorry, but I don't know what that means :-(";
    
    private static final String COMMAND_BYE_WORD = "bye";
    private static final String COMMAND_BYE_MESSAGE = TAB + "Bye. Hope to see you again soon!";
    
    private static final String COMMAND_DONE_WORD = "done";
    private static final String COMMAND_DONE_MESSAGE = TAB + "Nice! I've marked this task as done:";
    
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_ADD_MESSAGE =
            "Got it. I've added this task:" + LS + TAB + "%s" + LS + "Now " + "you have %d task(s) in the list.";
    
    private static final String COMMAND_INVALID_MESSAGE = "Command is invalid, " + "please try another command";
    private static final String COMMAND_INVALID_INDEX = "Index is invalid";
    
    private static final String ENTER_A_COMMAND = "Enter a command: ";
    private static final String COMMAND_ENTERED = "Command entered: ";
    
    private static final String MISSING_DATE_MESSAGE = "OOPS!!! The date of a %s cannot be empty.";
    private static final String MISSING_DESCRIPTION_MESSAGE = "OOPS!!! The description of a %s cannot be empty.";
    private static final String SPILT_BY_SPACE = "\\s+";
    private static final String SPILT_BY_SLASH = "/";
    
    private static Scanner in = new Scanner(System.in);
    
    
    private static final int MAX_CAPACITY = 100;
    private static Task[] tasks = new Task[MAX_CAPACITY];
    
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
            System.out.println(e);
        }
    }
    
    private static void operateCommand(String commandWord, String commandArgs, String commandDate) {
        switch (commandWord.toLowerCase()) {
        case COMMAND_TODO_WORD:
            tasks[Task.getTaskCount()] = new Todo(commandArgs);
            displayAddMessage();
            break;
        case COMMAND_DEADLINE_WORD:
            tasks[Task.getTaskCount()] = new Deadline(commandArgs, commandDate);
            displayAddMessage();
            break;
        case COMMAND_EVENT_WORD:
            tasks[Task.getTaskCount()] = new Event(commandArgs, commandDate);
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
        default:
            displayInvalidMessage();
        }
    }
    
    private static void hasEmptyDescription(String commandWord, String commandArgs) throws DukeException {
        if ((commandWord.equals(COMMAND_TODO_WORD) || commandWord.equals(COMMAND_DEADLINE_WORD) ||
                commandWord.equals(COMMAND_EVENT_WORD)) && commandArgs.equals("")) {
            throw new DukeException(String.format(MISSING_DESCRIPTION_MESSAGE, commandWord));
        }
    }
    
    private static void hasEmptyDate(String commandWord, String commandDate) throws DukeException {
        if ((commandWord.equals(COMMAND_DEADLINE_WORD) || commandWord.equals(COMMAND_EVENT_WORD)) &&
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
    
    private static void displayWelcomeMessage() {
        System.out.println(DIVIDER + LS + WELCOME_MESSAGE + LS + DIVIDER);
    }
    
    private static void displayAddMessage() {
        
        System.out.println(DIVIDER);
        System.out.println(String.format(COMMAND_ADD_MESSAGE, tasks[Task.getTaskCount() - 1], Task.getTaskCount()));
        System.out.println(DIVIDER);
    }
    
    private static void displayDoneMessage(String index) {
        try {
            int doneTaskIndex = Integer.parseInt(index) - 1;
            if (doneTaskIndex >= Task.getTaskCount()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            tasks[doneTaskIndex].markAsDone();
            System.out.println(DIVIDER + LS + COMMAND_DONE_MESSAGE);
            System.out.println(TAB + tasks[doneTaskIndex]);
            System.out.println(DIVIDER);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(DIVIDER + LS + COMMAND_INVALID_INDEX + LS + DIVIDER);
        }
    }
    
    private static void displayInvalidMessage() {
        try {
            throw new DukeException(COMMAND_INVALID_MESSAGE);
        } catch (DukeException e) {
            System.out.println(DIVIDER);
            System.out.println(e);
            System.out.println(DIVIDER);
        }
    }
    
    private static void displayBlahMessage() {
        try {
            throw new DukeException(COMMAND_BLAH_MESSAGE);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
    
    private static void displayByeMessageAndExit() {
        System.out.println(DIVIDER + LS + COMMAND_BYE_MESSAGE + LS + DIVIDER);
        System.exit(0);
    }
    
    private static void displayTaskList() {
        System.out.println(DIVIDER);
        if (Task.getTaskCount() == 0) {
            System.out.println(COMMAND_LIST_EMPTY);
            System.out.println(DIVIDER);
            return;
        }
        System.out.println(COMMAND_LIST_MESSAGE);
        for (int i = 0; i < Task.getTaskCount(); ++i) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        System.out.println(DIVIDER);
    }
    
}
