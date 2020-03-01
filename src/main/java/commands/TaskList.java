package commands;

import java.util.ArrayList;
import tasks.Task;

/**
 * Represents the helper functions used by commands package
 */
public class TaskList {

    public TaskList() {}

    /**
     * Returns the String format of index for done and delete operations
     * @param input String input by user
     * @param position number of characters to remove
     * @return String format of index
     */
    public String getStringIndex(String input, int position) {
        String removeTrailingSpaces = input.trim();
        String numericalIndex = removeTrailingSpaces.substring(position, input.length()).trim();
        return numericalIndex;
    }

    /**
     * Returns true if String is numeric. Otherwise, return false
     * @param number String to be checked against
     * @return boolean value
     */
    public boolean isNumeric(String number) {
        boolean isDigit = true;
        for (int i = 0; i < number.length(); i ++) {
            char letter = number.charAt(i);
            if (!Character.isDigit(letter)) {
                isDigit = false;
                break;
            }
        }
        return isDigit;
    }

    /**
     * Returns description of task
     * @param input String input by user
     * @param lengthOfCommand length of the command that the user input
     * @return description of task
     */
    public String getDescription(String input, int lengthOfCommand) {
        String removeCommand = input.substring(lengthOfCommand).trim();
        int index = removeCommand.indexOf("/");
        String description = removeCommand.substring(0,index);
        return description.trim();
    }

    /**
     * Returns date/time/location of task
     * @param input String input by user
     * @param lengthOfCommand length of command that user input
     * @return data/time/location of task
     */
    public String getDetails(String input, int lengthOfCommand) {
        String removeCommand = input.substring(lengthOfCommand).trim();
        int index = removeCommand.indexOf("/");
        String details = removeCommand.substring(index + 3).trim();
        return details.trim();
    }

    /**
     * Returns true if user input an empty details, otherwise false
     * @param input String input by user
     * @param lengthOfCommand length of command that user inputs
     * @return boolean value
     */
    public boolean isEmptyDetails(String input, int lengthOfCommand) {
        String details = getDetails(input, lengthOfCommand);
        if (details.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if user input an empty description, otherwise false
     * @param input String input by user
     * @param lengthOfCommand length of command that user inputs
     * @return boolean value
     */
    public boolean isEmptyDescription(String input, int lengthOfCommand) {
        String description = getDescription(input, lengthOfCommand);
        if (description.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return true if String is out of range, otherwise false
     * @param number String index to be checked against
     * @return boolean value
     */
    public boolean isOutOfRange(String number, ArrayList<Task> tasks) {
        int index = Integer.parseInt(number);
        return (index <= 0 || index > tasks.size());
    }

    /**
     * Return true if task is completed, otherwise false
     * @param number String index of task
     * @return boolean value
     */
    public boolean isCompleted(String number, ArrayList<Task> tasks) {
        int index = Integer.parseInt(number) - 1;
        Task completedTask = tasks.get(index);
        return completedTask.getStatus();
    }

    /**
     * Prints list of tasks in task list
     */
    public void printTasks(ArrayList<Task> tasks) {
        int position = 1;
        for (Task text: tasks) {
            System.out.println("    " + position + ". " + text);
            position ++;
        }
    }
}

