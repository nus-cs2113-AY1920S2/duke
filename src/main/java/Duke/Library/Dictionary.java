package Duke.Library;

import Duke.Task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {

    // Library of common words
    public static final int MAX_TASKS = 100;
    public static ArrayList<Task> TaskList = new ArrayList(MAX_TASKS);
    public static boolean run = true;
    public static Scanner sc = new Scanner(System.in);
    public static String userCommand;
    public static String description;
    public static final String BOT_NAME = "E.D.I.T.H.";
    public static final String BOT_LOGO = "\n"
            + " _______         ______           _________        __________          __     __                \n"
            + "|   ____|       |    _  |         |__    __|      |___    ___|        |  |   |  |               \n"
            + "|   |___        |   | |  |           |  |             |  |            |  |___|  |               \n"
            + "|    ___|       |   | |  |           |  |             |  |            |   ___   |               \n"
            + "|   |___    _   |   |_|  |   _     __|  |__     _     |  |       _    |  |   |  |    _          \n"
            + "|_______|  |_|  |_______/   |_|   |________|   |_|    |__|      |_|   |__|   |__|   |_|         \n";

    public static final String USERNAME = "USER";
    public static final String MESSAGE_WELCOME = "\n\tHello! I'm " + BOT_NAME + "\n\tWhat can I do for you?";
    public static final String MESSAGE_EXIT = "\tBye. Hope to see you again soon!";
    public static final String LINE_DIVIDER = "\n\t___________________________________________________________________________";
    public static final String MESSAGE_INVALID_COMMAND = "\tInvalid Command. Please try again\n";
    public static final String MESSAGE_DESC_EMPTY = "\tInvalid Command! Description cannot be empty!";
    public static final String MESSAGE_BY_EMPTY = "\tInvalid Command! BY cannot be empty!";
    public static final String MESSAGE_AT_EMPTY = "\tInvalid Command! AT cannot be empty!";
    public static final String MESSAGE_MARK_EMPTY = "\tInvalid Command! Nothing is marked!";
    public static final String COMMAND_HELP_WORD = "HELP";
    public static final String COMMAND_HELP_DESC = "\n" +
            "\tHere's the help list\n" +
            "\t1. todo [Description]\n" +
            "\t\tadd in a todo Duke.task, format ' todo [Description] '\n" +
            "\t2. deadline\n" +
            "\t\tadd in a deadline Duke.task, format ' deadline [Description] /by[Time] ' \n" +
            "\t3. event\n" +
            "\t\tadd in an event Duke.task, format ' event [Description] /at[Time] '\n" +
            "\t4. list\n" +
            "\t\tlist out all the tasks\n" +
            "\t5. clear\n" +
            "\t\tclear all items from Duke.task\n" +
            "\t6. mark\n" +
            "\t\tmark a Duke.task by its index, format ' mark [index] '\n" +
            "\t7. bye\n" +
            "\t\texit the program\n";
    public static final String COMMAND_LIST_WORD = "LIST";
    public static final String COMMAND_CLEAR_WORD = "CLEAR";
    public static final String COMMAND_MARK_WORD = "MARK";
    public static final String COMMAND_DEADLINE_WORD = "DEADLINE";
    public static final String COMMAND_DELETE_WORD = "DELETE";
    public static final String COMMAND_REMOVE_WORD = "REMOVE";
    public static final String COMMAND_EXIT_WORD = "EXIT";
    public static final String COMMAND_BYE_WORD = "BYE";
    public static final String COMMAND_EVENT_WORD = "EVENT";
    public static final String COMMAND_TODO_WORD = "TODO";
}
