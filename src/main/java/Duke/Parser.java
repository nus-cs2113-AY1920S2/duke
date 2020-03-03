package Duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the object which parses user input to relevant functions for the execution of commands.
 */
public class Parser {

    /**
     * Parses user commands to relevant functions to complete it while checking for user input errors.
     * @param taskArray the task list of the current session to be parsed to relevant command methods
     * @param lastShownList a  of tasks shown previously when the list or find command has been called
     * @param scanner a scanner object that takes in user input
     */
    public static void parseUserCommands(TaskList taskArray, Scanner scanner) {
        ArrayList<Task> lastShownList = (ArrayList<Task>) taskArray.tasks.clone();
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
                break;
            case "list":
                Ui.printTasks(taskArray);
                lastShownList.clear();
                lastShownList = (ArrayList<Task>) taskArray.tasks.clone();
                break;
            case "done":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                int taskDone = 0;
                try {
                    taskDone = Integer.valueOf(tokenizedInputs[1]) - 1;
                } catch (Exception e){
                    System.out.println("Sorry, invalid index entered.\n");
                    break;
                }
                if (checkOutOfBounds(lastShownList, taskDone)) break;
                if (checkInvalidTask(taskArray, lastShownList.get(taskDone))) break;
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(lastShownList.get(taskDone).markAsDone());
                break;
            case "delete":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                int taskToDelete = 0;
                try {
                    taskToDelete = Integer.valueOf(tokenizedInputs[1]) - 1;
                } catch (Exception e){
                    System.out.println("Sorry, invalid index entered.\n");
                    break;
                }
                if (checkOutOfBounds(lastShownList, taskToDelete)) break;
                if (checkInvalidTask(taskArray, lastShownList.get(taskToDelete))) break;
                Ui.respondDeleteSuccess(taskArray.size-1, lastShownList.get(taskToDelete));
                taskArray.deleteTask(lastShownList.get(taskToDelete));
                break;
            case "todo":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                taskArray.addTask(new ToDo(tokenizedInputs[1]));
                Ui.respondAddedSuccess(taskArray.size, taskArray.get(taskArray.size-1));
                break;
            case "deadline":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                String[] deadlineInfo = tokenizedInputs[1].split(" /by ");
                if (checkDateEntered(deadlineInfo)) break;
                taskArray.addTask(new Deadline(deadlineInfo[0], deadlineInfo[1]));
                Ui.respondAddedSuccess(taskArray.size, taskArray.get(taskArray.size-1));
                break;
            case "event":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                String[] eventInfo = tokenizedInputs[1].split(" /at ");
                if (checkDateEntered(eventInfo)) break;
                taskArray.addTask(new Event(eventInfo[0], eventInfo[1]));
                Ui.respondAddedSuccess(taskArray.size, taskArray.get(taskArray.size-1));
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
        }
    }

    /**
     * Checks if index supplied is out of bounds.
     * @param lastShownList the last shown list from 'list' or 'find' commands.
     * @param index the index supplied by user input from 'delete' or 'done' commands
     * @return true if out of bounds and false if it is within bounds.
     */
    private static boolean checkOutOfBounds(ArrayList<Task> lastShownList, int index) {
        if (index >= lastShownList.size()) {
            System.out.println("Sorry, the task does not exist. Please use the 'list' or 'find' command for an updated list of tasks.\n");
            return true;
        }

        if (index < 0) {
            System.out.println("Sorry, invalid index entered.\n");
            return true;
        }
        return false;
    }

    /**
     * Checks if the task to be marked as done or deleted still exists as the last shown list may not be the most updated.
     * For example, the user may have deleted and added new tasks since the last time the list or find command was called.
     * @param taskArray the task array to check if it contains the chosen task extracted from the last shown list.
     * @param requestedTask the task from the last shown list chosen by referencing its index
     * @return true if the task is no longer valid and false if it is valid
     */
    public static boolean checkInvalidTask (TaskList taskArray, Task requestedTask) {
        if (taskArray.taskExists(requestedTask) == false) {
            System.out.println("Sorry the task no longer exists. Please use 'list' or 'find' for an updated list\n");
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