package alie;

import alie.task.Deadlines;
import alie.task.Events;
import alie.task.ToDo;

import java.util.Scanner;

public class Alie {

    protected static final int DONE_CMD_LENGTH = 5;
    protected static final int TODO_CMD_LENGTH = 5;
    protected static final String DEADLINE_DETAIL_DIVIDER = " /by ";
    protected static final String EVENT_DETAILS_DIVIDER = " /at ";

    public static void main(String[] args) {
        printWelcomeMsg();

        TaskManager checkList = new TaskManager();
        Scanner userInput = new Scanner(System.in);

        while (true) {
            printHeader();
            String cmd = getUserInput(userInput);
            try {
                parseThenExecuteCmd(cmd, checkList);
            } catch (Exception errorMsg) {
                System.out.println(errorMsg);
            }
        }
    }

    public static void printWelcomeMsg() {
        String logo =
                          "    /\\       |        |   |‾‾‾‾‾    \n"
                        + "   /  \\      |        |   |         \n"
                        + "  /____\\     |        |   |----     \n"
                        + " /      \\    |        |   |         \n"
                        + "/        \\ . |_____ . | . |_____ .  \n";
        System.out.println("Hello from\n" + logo);
        printHeader();
        System.out.println("What would you like to do?");
    }

    public static void printHeader() {
        System.out.print("ALIE> ");
    }

    private static String getUserInput(Scanner userInput) {
        return userInput.nextLine();
    }

    private static void parseThenExecuteCmd(String cmd, TaskManager checkList)
            throws InvalidCmdException {
        String[] splitCmds = cmd.split(" ", 2);
        String cmdType = splitCmds[0].toLowerCase();

        switch (cmdType) {
        case "bye":
            //Exiting A.L.I.E
            exitAlie(cmd, splitCmds);
        case "list":
            //Print list with all tasks
            printChecklist(checkList, splitCmds);
            break;
        case "done":
            //Mark task as complete
            markAsDone(cmd, checkList);
            break;
        case "todo":
            //Input format: <task type> <task name>
            addToDoTask(cmd, checkList);
            break;
        case "deadline":
            // Input format 1: <task type> <task name> /by <task details>
            addDeadlineTask(cmd, checkList);
            break;
        case "event":
            // Input format 1: <task type> <task name> /at <task details>
            addEventTask(cmd, checkList);
            break;
        default:
            throw new InvalidCmdException("Unable to execute \"" + cmd +
                    "\". Please try again with valid command.");
        }
    }

    private static void exitAlie(String cmd, String[] splitCmds) throws InvalidCmdException {
        if (splitCmds.length > 1 ) {
            throw new InvalidCmdException("To exit, use cmd: \"bye\".");
        }
        System.out.println("Bye-bye!");
        System.exit(0);
    }

    private static void printChecklist(TaskManager checkList, String[] splitCmds)
            throws InvalidCmdException {
        if (splitCmds.length > 1 ) {
            throw new InvalidCmdException("Unable to append info to cmd: \"list\".");
        }
        checkList.printAllTasksAdded();
        return;
    }

    private static void markAsDone(String cmd, TaskManager checkList) throws InvalidCmdException {
        //Input format: done: <task index>
        try {
            int indexOfTask = Integer.parseInt(cmd.substring(DONE_CMD_LENGTH));
            checkList.markTaskCompleted(indexOfTask - 1);
        } catch (NumberFormatException error) {
            throw new InvalidCmdException("INDEX provided is not a number.");
        } catch (IndexOutOfBoundsException error) {
            throw new InvalidCmdException("INDEX provided is not a valid index.");
        } catch (NullPointerException error) {
            throw new InvalidCmdException("INDEX provided is not a valid index.");
        }
        return;
    }

    private static void addToDoTask(String cmd, TaskManager checkList) throws InvalidCmdException {
        String taskName;
        try {
            taskName = cmd.substring(TODO_CMD_LENGTH);
            if (taskName.equalsIgnoreCase("")) {
                throw new InvalidCmdException("DESCRIPTION of TODO is missing.");
            }
            checkList.addNewTask(new ToDo(taskName));
        } catch (StringIndexOutOfBoundsException error) {
            throw new InvalidCmdException("DESCRIPTION of TODO is missing.");
        }
        return;
    }

    private static void addDeadlineTask(String cmd, TaskManager checkList)
            throws InvalidCmdException {
        int detailsDividerId;
        String taskName;
        String taskDetails;
        try {
            detailsDividerId = cmd.indexOf(DEADLINE_DETAIL_DIVIDER);
            taskName = cmd.substring(0, detailsDividerId).trim();
            taskDetails = cmd.substring(detailsDividerId + 1).trim();
            checkList.addNewTask(new Deadlines(taskName, taskDetails));
        } catch (StringIndexOutOfBoundsException error) {
            throw new InvalidCmdException("DESCRIPTION and DATE of deadline is missing.");
        }
        return;
    }

    private static void addEventTask(String cmd, TaskManager checkList) throws InvalidCmdException {
        int detailsDividerId;
        String taskName;
        String taskDetails;
        try {
            detailsDividerId = cmd.indexOf(EVENT_DETAILS_DIVIDER);
            taskName = cmd.substring(0, detailsDividerId).trim();
            taskDetails = cmd.substring(detailsDividerId + 1).trim();
            checkList.addNewTask(new Events(taskName, taskDetails));
        } catch (StringIndexOutOfBoundsException error) {
            throw new InvalidCmdException("DESCRIPTION and DATE of event is missing.");
        }
    }
}
