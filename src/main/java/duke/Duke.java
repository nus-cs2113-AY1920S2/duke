package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeLoadingException;
import duke.exception.DukeNullDescriptionException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskManager;
import duke.exception.DukeException;

import static duke.util.Constants.DATA_FILE_NAME;
import static duke.util.Constants.DEADLINE_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.DELETE_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.DONE_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.EVENT_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.EXIT_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.LIST_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.TODO_COMMAND_HELP_MESSAGE;
import static duke.util.Constants.HELP_COMMAND;
import static duke.util.Constants.LINE_DIVIDER;
import static duke.util.Constants.FIVE_SPACES;
import static duke.util.Constants.EXIT_COMMAND_BYE;
import static duke.util.Constants.EXIT_COMMAND_QUIT;
import static duke.util.Constants.EXIT_COMMAND_EXIT;
import static duke.util.Constants.LIST_COMMAND;
import static duke.util.Constants.LIST_COMMAND_SHORTCUT;
import static duke.util.Constants.DONE_COMMAND;
import static duke.util.Constants.TODO_COMMAND;
import static duke.util.Constants.TODO_COMMAND_SHORTCUT;
import static duke.util.Constants.DEADLINE_COMMAND;
import static duke.util.Constants.DEADLINE_COMMAND_SHORTCUT;
import static duke.util.Constants.EVENT_COMMAND;
import static duke.util.Constants.EVENT_COMMAND_SHORTCUT;
import static duke.util.Constants.DELETE_COMMAND;
import static duke.util.Constants.DELETE_COMMAND_SHORTCUT;
import static duke.util.Constants.DEADLINE_TIME_DELIMITER;
import static duke.util.Constants.EVENT_TIME_DELIMITER;
import static duke.util.Constants.UNKNOWN_COMMAND_RESPONSE;
import static duke.util.Constants.DEADLINE_FORMAT_ERROR_MESSAGE;
import static duke.util.Constants.EVENT_FORMAT_ERROR_MESSAGE;
import static duke.util.Constants.CRYING_FACE;


public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke()  {
        ui = new Ui();
        storage = new Storage(DATA_FILE_NAME);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeLoadingException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }


    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
/*

    private static void processUserInput(TaskManager taskMgr, String userInput) {
        String[] commands = splitCommand(userInput);
        String commandWord = commands[0];
        String afterCommand = commands[1];
        switch (commandWord) {
        case EXIT_COMMAND_BYE:
        case EXIT_COMMAND_EXIT:
        case EXIT_COMMAND_QUIT:
            taskMgr.saveDataToFile();
            break;
        case LIST_COMMAND:
        case LIST_COMMAND_SHORTCUT:
            taskMgr.listTasks();
            break;
        case DONE_COMMAND:
            int doneTaskId = extractTaskId(userInput);
            taskMgr.markAsDone(doneTaskId);
            break;
        case TODO_COMMAND:
        case TODO_COMMAND_SHORTCUT:
            taskMgr.addTask(afterCommand, TODO_COMMAND);
            break;
        case DEADLINE_COMMAND:
        case DEADLINE_COMMAND_SHORTCUT:
            processTaskWithTime(taskMgr, afterCommand, DEADLINE_TIME_DELIMITER, DEADLINE_COMMAND,
                    DEADLINE_FORMAT_ERROR_MESSAGE);
            break;
        case EVENT_COMMAND:
        case EVENT_COMMAND_SHORTCUT:
            processTaskWithTime(taskMgr, afterCommand, EVENT_TIME_DELIMITER, EVENT_COMMAND, EVENT_FORMAT_ERROR_MESSAGE);
            break;
        case DELETE_COMMAND:
        case DELETE_COMMAND_SHORTCUT:
            int delTaskId = extractTaskId(userInput);
            taskMgr.delTask(delTaskId);
            break;
        case HELP_COMMAND:
            printHelpMessage(afterCommand);
            break;
        default:
            respondToUnknownCommand();
        }
    }

    private static void processTaskWithTime(TaskManager taskMgr, String taskInfo, String timeDelimiter,
                                            String command, String formatErrorMessage) {
        try {
            String taskDescriptions = extractTaskDescription(taskInfo, timeDelimiter);
            String taskTime = extractTaskTime(taskInfo, timeDelimiter);
            taskMgr.addTask(taskDescriptions, taskTime, command);
        } catch (DukeException e) {
            taskMgr.printFormatErrorMsg(formatErrorMessage);
        }
    }

    private static void respondToUnknownCommand() {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES + CRYING_FACE + UNKNOWN_COMMAND_RESPONSE);
        System.out.println(LINE_DIVIDER);
    }

    private static String[] splitCommand(String userInput) {
        String commandWord = extractCommandWord(userInput);
        String afterCommand = userInput.substring(commandWord.length()).trim();
        String[] commands = new String[]{commandWord, afterCommand};
        return commands;
    }

    private static String extractCommandWord(String userInput) {
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

    private static int extractTaskId(String userInput) {
        int taskIdIndex = userInput.indexOf(" ") + 1;
        String doneTaskIdString = userInput.substring(taskIdIndex);
        try {
            return Integer.parseInt(doneTaskIdString);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void printHelpMessage(String commandWord) {
        System.out.println(LINE_DIVIDER);
        switch (commandWord) {
        case EXIT_COMMAND_BYE:
        case EXIT_COMMAND_EXIT:
        case EXIT_COMMAND_QUIT:
            System.out.println(FIVE_SPACES + EXIT_COMMAND_HELP_MESSAGE);
            break;
        case LIST_COMMAND:
        case LIST_COMMAND_SHORTCUT:
            System.out.println(FIVE_SPACES + LIST_COMMAND_HELP_MESSAGE);
            break;
        case DONE_COMMAND:
            System.out.println(FIVE_SPACES + DONE_COMMAND_HELP_MESSAGE);
            break;
        case TODO_COMMAND:
        case TODO_COMMAND_SHORTCUT:
            System.out.println(FIVE_SPACES + TODO_COMMAND_HELP_MESSAGE);
            break;
        case DEADLINE_COMMAND:
        case DEADLINE_COMMAND_SHORTCUT:
            System.out.println(FIVE_SPACES + DEADLINE_COMMAND_HELP_MESSAGE);
            break;
        case EVENT_COMMAND:
        case EVENT_COMMAND_SHORTCUT:
            System.out.println(FIVE_SPACES + EVENT_COMMAND_HELP_MESSAGE);
            break;
        case DELETE_COMMAND:
        case DELETE_COMMAND_SHORTCUT:
            System.out.println(FIVE_SPACES + DELETE_COMMAND_HELP_MESSAGE);
            break;
        default:
            System.out.println(FIVE_SPACES + EXIT_COMMAND_HELP_MESSAGE);
            System.out.println(FIVE_SPACES + LIST_COMMAND_HELP_MESSAGE);
            System.out.println(FIVE_SPACES + DONE_COMMAND_HELP_MESSAGE);
            System.out.println(FIVE_SPACES + TODO_COMMAND_HELP_MESSAGE);
            System.out.println(FIVE_SPACES + DEADLINE_COMMAND_HELP_MESSAGE);
            System.out.println(FIVE_SPACES + EVENT_COMMAND_HELP_MESSAGE);
            System.out.println(FIVE_SPACES + DELETE_COMMAND_HELP_MESSAGE);
        }
        System.out.println(LINE_DIVIDER);
    }
*/

    public static void main(String[] args) {
        new Duke().run();
        /*
        TaskManager taskMgr = new TaskManager();

        greet();

        String input;
        Scanner s = new Scanner(System.in);
        do {
            input = s.nextLine();
            processUserInput(taskMgr, input);
        } while (!isExited);
         */
    }
}
