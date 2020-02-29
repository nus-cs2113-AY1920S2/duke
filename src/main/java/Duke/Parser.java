package Duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static void parseUserCommands(TaskList taskArray, ArrayList<Task> lastShownList, Scanner scanner) {
        boolean lastShownListOutdated = false;
        boolean exit = false;
        while (exit == false) {
            String userInput = scanner.nextLine();
            String[] tokenizedInputs = userInput.split(" ", 2);
            String instruction = tokenizedInputs[0];

            switch (instruction) {
            case "bye":
                System.out.println(Duke.GOODBYE);
                exit = true;
                Storage.saveTasks(Duke.FILEPATH, taskArray);
                break;
            case "find":
                String keyword = tokenizedInputs[1];
                lastShownList.clear();
                Ui.displayMatchingTasks(taskArray, lastShownList, keyword);
                lastShownListOutdated = false;
                break;
            case "list":
                Ui.printTasks(taskArray);
                lastShownList.clear();
                lastShownList = (ArrayList<Task>) taskArray.tasks.clone();
                lastShownListOutdated = false;
                break;
            case "done":
                if (checkOutdatedIndex(lastShownListOutdated)) break;
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                int taskDone = Integer.valueOf(tokenizedInputs[1]) - 1;
                //to do more error handling here for index out of bounds
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(lastShownList.get(taskDone).markAsDone());
                lastShownListOutdated = true;
                break;
            case "delete":
                if (checkOutdatedIndex(lastShownListOutdated)) break;
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                int taskToDelete = Integer.valueOf(tokenizedInputs[1]) - 1;
                //to do more error handling here for index out of bounds
                Ui.respondDeleteSuccess(taskArray.size-1, lastShownList.get(taskToDelete));
                taskArray.deleteTask(lastShownList.get(taskToDelete));
                lastShownListOutdated = true;
                break;
            case "todo":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                taskArray.addTask(new ToDo(tokenizedInputs[1]));
                Ui.respondAddedSuccess(taskArray.size, taskArray.get(taskArray.size-1));
                lastShownListOutdated = true;
                break;
            case "deadline":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                String[] deadlineInfo = tokenizedInputs[1].split(" /by ");
                if (checkDateEntered(deadlineInfo)) break;
                taskArray.addTask(new Deadline(deadlineInfo[0], deadlineInfo[1]));
                Ui.respondAddedSuccess(taskArray.size, taskArray.get(taskArray.size-1));
                lastShownListOutdated = true;
                break;
            case "event":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                String[] eventInfo = tokenizedInputs[1].split(" /at ");
                if (checkDateEntered(eventInfo)) break;
                taskArray.addTask(new Event(eventInfo[0], eventInfo[1]));
                Ui.respondAddedSuccess(taskArray.size, taskArray.get(taskArray.size-1));
                lastShownListOutdated = true;
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
        }
    }

    private static boolean checkOutdatedIndex(boolean lastShownListOutdated) {
        if (lastShownListOutdated == true) {
            System.out.println("Sorry, the list index has been modified, please use 'list' or 'find' command to get" +
                    " updated indexes before proceeding.\n");
            return true;
        }
        return false;
    }

    /**
     * Checks if a date has been entered for new Events and Deadlines.
     * @param information the information portion of the user input, further split into the description and date
     *                    portions.
     * @return true if the date portion is empty and false if the date portion is valid
     */
    static boolean checkDateEntered(String[] information) {
        if (information.length == 1) {
            System.out.println("☹ OOPS!!! You did not enter a date");
            return true;
        }
        return false;
    }

    /**
     * Checks if the description portion of a command is empty.
     *
     * @param tokens an array containing the user input tokenized into the instruction and the task information
     * @param instruction the instruction requested
     * @return true if the description is empty and false if it is not empty
     */
    static boolean checkEmptyDescription(String[] tokens, String instruction) {
        if (tokens.length == 1) {
            System.out.println("☹ OOPS!!! The description of a " + instruction + " cannot be empty.");
            return true;
        }
        return false;
    }
}