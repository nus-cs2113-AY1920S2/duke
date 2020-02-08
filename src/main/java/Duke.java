import task.TaskManager;

import java.util.Scanner;
import static util.Constants.*;

public class Duke {

    private static void greet() {
        System.out.println(LOGO);
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES+GREETING_WORD);
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
            respondToUnknownCommand();
        }
    }

    private static void respondToUnknownCommand() {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES+CRYING_FACE+UNKNOWN_COMMAND_RESPONSE);
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
        System.out.println(FIVE_SPACES+BYE_WORD);
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
