import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = "          __ __      _           __                          \n" +
            "     /\\  /_ /_ |    (_)         / _|                         \n" +
            "    /  \\  | || |_ __ _ ___  ___| |_ ___  _ __ _ __ ___   ___ \n" +
            "   / /\\ \\ | || | '__| / __|/ _ \\  _/ _ \\| '__| '_ ` _ \\ / _ \\\n" +
            "  / ____ \\| || | |  | \\__ \\  __/ || (_) | |  | | | | | |  __/\n" +
            " /_/    \\_\\_||_|_|  |_|___/\\___|_| \\___/|_|  |_| |_| |_|\\___|\n";
    private static final String LINE_DIVIDER = "    ____________________________________________________________";
    private static final String GREETING_WORD = "     Hello! I'm A11riseforme\n     What can I do for you?";
    private static final String BYE_WORD = "     Bye. Hope to see you again soon!";
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_TIME_DELIMITER = " /by ";
    private static final String EVENT_TIME_DELIMITER = " /at ";

    private static void greet() {
        System.out.println(LOGO);
        System.out.println(LINE_DIVIDER);
        System.out.println(GREETING_WORD);
        System.out.println(LINE_DIVIDER);
    }

    private static void processUserInput(TaskManager TaskMgr, String userInput) {
        String command = extractCommand(userInput);
        String taskInfo = extractTaskInfo(userInput);
        String taskTime;
        String taskDescriptions;
        switch (command) {
        case EXIT_COMMAND:
            bye();
            break;
        case LIST_COMMAND:
            TaskMgr.listTasks();
            break;
        case DONE_COMMAND:
            int doneTaskID = extractDoneTaskID(userInput);
            TaskMgr.markAsDone(doneTaskID);
            break;
        case TODO_COMMAND:
            TaskMgr.addTasks(taskInfo, TODO_COMMAND);
            break;
        case DEADLINE_COMMAND:
            taskDescriptions = extractTaskDescription(taskInfo, DEADLINE_TIME_DELIMITER);
            taskTime = extractTaskTime(taskInfo, DEADLINE_TIME_DELIMITER);
            TaskMgr.addTasks(taskDescriptions, taskTime, DEADLINE_COMMAND);
            break;
        case EVENT_COMMAND:
            taskDescriptions = extractTaskDescription(taskInfo, EVENT_TIME_DELIMITER);
            taskTime = extractTaskTime(taskInfo, EVENT_TIME_DELIMITER);
            TaskMgr.addTasks(taskDescriptions, taskTime, EVENT_COMMAND);
            break;
        default:
            // default will add to todo
            TaskMgr.addTasks(userInput);
        }
    }

    private static String extractCommand(String userInput) {
        int spaceIndex = userInput.indexOf(' ');
        if (spaceIndex == -1) {
            return userInput;
        }
        return userInput.substring(0, spaceIndex);
    }

    private static String extractTaskInfo(String userInput) {
        int spaceIndex = userInput.indexOf(' ');
        if (spaceIndex == -1) {
            return userInput;
        }
        return userInput.substring(spaceIndex+1);
    }

    private static String extractTaskTime(String taskInfo, String delimiter) {
        int taskTimeIndex = taskInfo.indexOf(delimiter) + delimiter.length();
        return taskInfo.substring(taskTimeIndex);
    }

    private static String extractTaskDescription(String taskInfo, String delimiter) {
        int taskTimeIndex = taskInfo.indexOf(delimiter);
        return taskInfo.substring(0, taskTimeIndex);
    }

    private static int extractDoneTaskID(String userInput) {
        int taskIDIndex = userInput.indexOf(" ")+1;
        String doneTaskIDString = userInput.substring(taskIDIndex);
        return Integer.parseInt(doneTaskIDString);
    }

    private static void bye() {
        System.out.println(LINE_DIVIDER);
        System.out.println(BYE_WORD);
        System.out.println(LINE_DIVIDER);
    }

    public static void main(String[] args) {
        TaskManager TaskMgr = new TaskManager(100);

        greet();

        String input;
        Scanner s  = new Scanner(System.in);
        do {
            input = s.nextLine();
            processUserInput(TaskMgr, input);
        } while (!input.equals(EXIT_COMMAND));
    }
}
