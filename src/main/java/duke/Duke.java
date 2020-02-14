package duke;

import java.util.Scanner;

import duke.util.TaskManager;
import duke.exception.DukeException;

// I know I shouldn't do this, but I am too tired now.
import static duke.util.Constants.*;

public class Duke {

    private static void greet() {
        System.out.println(LOGO);
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES + GREETING_WORD);
        System.out.println(LINE_DIVIDER);
    }

    private static void processUserInput(TaskManager TaskMgr, String userInput) {
        String command = extractCommand(userInput);
        String taskInfo = extractTaskInfo(userInput);
        switch (command) {
        case EXIT_COMMAND_BYE:
        case EXIT_COMMAND_EXIT:
        case EXIT_COMMAND_QUIT:
            bye();
            TaskMgr.saveDataToFile();
            break;
        case LIST_COMMAND:
        case LIST_COMMAND_SHORTCUT:
            TaskMgr.listTasks();
            break;
        case DONE_COMMAND:
            int doneTaskID = extractTaskID(userInput);
            TaskMgr.markAsDone(doneTaskID);
            break;
        case TODO_COMMAND:
        case TODO_COMMAND_SHORTCUT:
            TaskMgr.addTask(taskInfo, TODO_COMMAND);
            break;
        case DEADLINE_COMMAND:
        case DEADLINE_COMMAND_SHORTCUT:
            processTaskWithTime(TaskMgr, taskInfo, DEADLINE_TIME_DELIMITER, DEADLINE_COMMAND, DEADLINE_FORMAT_ERROR_MESSAGE);
            break;
        case EVENT_COMMAND:
        case EVENT_COMMAND_SHORTCUT:
            processTaskWithTime(TaskMgr, taskInfo, EVENT_TIME_DELIMITER, EVENT_COMMAND, EVENT_FORMAT_ERROR_MESSAGE);
            break;
        case DELETE_COMMAND:
        case DELETE_COMMAND_SHORTCUT:
            int delTaskID = extractTaskID(userInput);
            TaskMgr.delTask(delTaskID);
            break;
        default:
            respondToUnknownCommand();
        }
    }

    private static void processTaskWithTime(TaskManager TaskMgr, String taskInfo, String timeDelimiter, String deadlineCommand, String formatErrorMessage) {
        try {
            String taskDescriptions = extractTaskDescription(taskInfo, timeDelimiter);
            String taskTime = extractTaskTime(taskInfo, timeDelimiter);
            TaskMgr.addTask(taskDescriptions, taskTime, deadlineCommand);
        } catch (DukeException e) {
            TaskMgr.printErrorMsg(formatErrorMessage);
        }
    }

    private static void respondToUnknownCommand() {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES + CRYING_FACE + UNKNOWN_COMMAND_RESPONSE);
        System.out.println(LINE_DIVIDER);
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
            return "";
        }
        return userInput.substring(spaceIndex + 1);
    }

    private static String extractTaskTime(String taskInfo, String delimiter) {
        int taskTimeIndex = taskInfo.indexOf(delimiter) + delimiter.length();
        return taskInfo.substring(taskTimeIndex);
    }

    private static String extractTaskDescription(String taskInfo, String delimiter) throws DukeException {
        int taskTimeIndex = taskInfo.indexOf(delimiter);
        try {
            return taskInfo.substring(0, taskTimeIndex);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }

    private static int extractTaskID(String userInput) {
        int taskIDIndex = userInput.indexOf(" ") + 1;
        String doneTaskIDString = userInput.substring(taskIDIndex);
        try {
            return Integer.parseInt(doneTaskIDString);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void bye() {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES + BYE_WORD);
        System.out.println(LINE_DIVIDER);
    }

    public static void main(String[] args) {
        TaskManager TaskMgr = new TaskManager(DATA_FILE_PATH);

        greet();

        String input;
        Scanner s = new Scanner(System.in);
        do {
            input = s.nextLine();
            processUserInput(TaskMgr, input);
        } while (!input.equals(EXIT_COMMAND_BYE));
    }
}
