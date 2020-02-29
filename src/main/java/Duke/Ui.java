package Duke;

import java.util.ArrayList;

public class Ui {

    /**
     * Displays a list of tasks that have descriptions which contain the keyword searched.
     * @param taskArray the full task array to search
     * @param lastShownList the last shown list to be overwritten when this function is called
     * @param keyword the search term to be matched against task descriptions
     * @see Ui#printFindResults(ArrayList, int) printFindResults
     */
    public static void displayMatchingTasks(TaskList taskArray, ArrayList<Task> lastShownList, String keyword) {
        int lastShownListSize = 0;
        for (Task i : taskArray.tasks) {
            if (i.description.contains(keyword)) {
                lastShownList.add(i);
                lastShownListSize++;
            }
        }
        if (lastShownListSize > 0) {
            System.out.println("____________________________________________________________\n" +
                    "     Here are the matching tasks in your list:\n");
            Ui.printFindResults(lastShownList, lastShownListSize);
            System.out.println("____________________________________________________________\n");
        } else {
            System.out.println("There are no tasks matching that description.\n");
        }

    }

    /**
     * Prints a messages to user to show the task is going to be deleted from the task array.
     * @param taskListSize the size of the task array after deletion
     * @param task the task to be deleted
     */
    public static void respondDeleteSuccess(int taskListSize, Task task) {

        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task:\n" + task.toString());
        System.out.print("Now you have ");
        System.out.print(taskListSize);
        if (taskListSize == 1) {
            System.out.print(" task in the list.\n" +
                    "____________________________________________________________\n");
        } else {
            System.out.print(" tasks in the list.\n" +
                    "____________________________________________________________\n");
        }
    }

    /**
     * Prints a message to the user to show that the task has been added successfully to the task array.
     * @param taskListSize the number of tasks in the array
     * @param task the new task that was just added
     */
    public static void respondAddedSuccess(int taskListSize, Task task) {
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" + task.toString());
        System.out.print("Now you have ");
        System.out.print(taskListSize);
        if (taskListSize <= 0) {
            System.out.print(" task in the list.\n" +
                    "____________________________________________________________");
        } else {
            System.out.print(" tasks in the list.\n" +
                    "____________________________________________________________\n");
        }
    }

    /**
     * Prints all tasks in the task array.
     * @param taskArray the full array of tasks
     */
    public static void printTasks(TaskList taskArray) {
        for (int i = 1; i <= taskArray.size; i++) {
            System.out.print(i);
            System.out.println("." + taskArray.get(i - 1).toString());
        }
    }

    /**
     * Prints the results from the {@link Ui#displayMatchingTasks(TaskList, ArrayList, String) displayMatchingTasks} method which provides a list of matching tasks to print.
     * @param results a list of tasks matching the keyword queried
     * @param size size of the list of results
     */
    public static void printFindResults (ArrayList <Task> results, int size) {
        for (int i = 1; i <= size; i++) {
            System.out.print(i);
            System.out.println("." + results.get(i - 1).toString());
        }
    }
}