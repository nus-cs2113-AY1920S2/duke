import java.util.Scanner;
public class Duke {

    private static final String DIVIDER = "____________________________________________________________";
    private static final String LS = System.lineSeparator();
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke" + LS + "What can i do for you?";
    private static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_LIST_EMPTY = "\tThe list is empty!";

    private static final String COMMAND_BLAH_WORD = "blah";
    private static final String COMMAND_BLAH_MESSAGE = "\tblah";

    private static final String COMMAND_BYE_WORD = "bye";
    private static final String COMMAND_BYE_MESSAGE = "\tBye. Hope to see you again soon!";

    private static final String COMMAND_DONE_WORD = "done";
    private static final String COMMAND_DONE_MESSAGE = "\tNice! I've marked this task as done: ";

    private static final String COMMAND_ADD_MESSAGE = "\tadded: ";

    private static final int MAX_CAPACITY = 100;
    private static Task[] tasks = new Task[MAX_CAPACITY];

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        displayWelcomeMessage();
        while (true) {
            String userInput = getUserInput();
            if (userInput.equalsIgnoreCase(COMMAND_LIST_WORD)) {
                printTaskList();
            } else if (userInput.equalsIgnoreCase(COMMAND_BLAH_WORD)) {
                displayBlahMessage();
            } else if (userInput.equalsIgnoreCase(COMMAND_BYE_WORD)) {
                displayByeMessage();
                break;
            } else if (userInput.toLowerCase().startsWith(COMMAND_DONE_WORD)) {
                displayDoneMessage(userInput);
            } else {
                tasks[Task.getTaskCount()] = new Task(userInput);
                displayAddMessage(userInput);
            }
        }
    }

    private static void displayWelcomeMessage() {
        System.out.println("Hello from\n" + DUKE_LOGO);
        System.out.println(DIVIDER + LS + WELCOME_MESSAGE + LS + DIVIDER);
    }

    private static void displayAddMessage(String userInput) {
        System.out.println(DIVIDER + LS + COMMAND_ADD_MESSAGE + userInput + LS + DIVIDER);
    }

    private static void displayDoneMessage(String userInput) {
        int doneTaskIndex =  Integer.parseInt(userInput.substring(5)) - 1;
        tasks[doneTaskIndex].markAsDone();
        System.out.println(DIVIDER + LS + COMMAND_DONE_MESSAGE);
        System.out.println("\t\t" + tasks[doneTaskIndex].getStatusIcon() + tasks[doneTaskIndex].getDescription());
        System.out.println(DIVIDER);
    }

    private static void displayBlahMessage() {
        System.out.println(DIVIDER + LS + COMMAND_BLAH_MESSAGE + LS + DIVIDER);
    }

    private static void displayByeMessage() {
        System.out.println(DIVIDER + LS + COMMAND_BYE_MESSAGE + LS + DIVIDER);
    }

    private static String getUserInput() {
        System.out.print("Enter a command: ");
        String inputLine = in.nextLine();
        inputLine = inputLine.trim();
        return inputLine;
    }

    private static void printTaskList() {
        System.out.println(DIVIDER);
        if(Task.getTaskCount() > 0) {
            for (int i = 0; i < Task.getTaskCount(); ++i) {
                System.out.println((i+1) + "." + tasks[i].getStatusIcon() + tasks[i].getDescription());
            }
        } else {
            System.out.println(COMMAND_LIST_EMPTY);
        }
        System.out.println(DIVIDER);
    }

}
