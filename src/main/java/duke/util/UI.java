package duke.util;

import duke.exceptions.IllegalClearException;
import duke.taskmanager.Tasks;

import java.util.List;
import java.util.Scanner;

public class UI {
    /**
     * Contains the Scanner class, as Ui is where
     * the scanner object is initialised for user input to be read.
     */

    private static Scanner userInput = new Scanner(System.in);

    public String FORMAT = Split.FORMAT.getSplit();
    public String SPLIT_UPPER_BOUNDARY = Split.SPLIT_UPPER_BOUNDARY.getSplit();
    public String SPLIT_LOWER_BOUNDARY = Split.SPLIT_LOWER_BOUNDARY.getSplit();

    public UI(){
    }

    /**
     * Returns a String user input that will be further used in other methods.
     * @return      the user input at designated place
     */
    public String getStringInput() {
        return userInput.nextLine();
    }

    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo);
    }
    public void printDoneIntro() {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "Please select the task that you have ");
        System.out.printf(FORMAT, "    completed (select the no)");
        TaskList.showList();
    }
    public void printDeleteIntro() {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "Please select the task that you wish ");
        System.out.printf(FORMAT, "to delete (select the no)");
        TaskList.showList();
    }
    public void printExit() {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "Bye. Hope to see you again soon!");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }

    public void printExeType() {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "Hi what can I do for you? " +
                "(please key in the number):" );
        System.out.printf(FORMAT, "1. Add a new task,");
        System.out.printf(FORMAT, "2. Show my tasks,");
        System.out.printf(FORMAT, "3. I've completed my task!");
        System.out.printf(FORMAT, "4. Delete a task");
        System.out.printf(FORMAT, "5. Find a task");
        System.out.printf(FORMAT, "6. Clear all tasks, or");
        System.out.printf(FORMAT, "7. See you next time!");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }

    public void printTaskType() {
        String format = "0O=-    %-69s-=O0%n";
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(format, "Which category does your task belong to?");
        System.out.printf(format, "1. ToDos: tasks without any date/time attached to it ");
        System.out.printf(format, "   (e.g., visit new theme park)");
        System.out.printf(format, "2. Deadlines: tasks that need to be done before a specific");
        System.out.printf(format, "   date/time (e.g., submit report by 11/10/2019 5pm)");
        System.out.printf(format, "3. Events: tasks that start at a specific time and ends");
        System.out.printf(format, "   at a specific time(e.g., team project meeting on 2/10/2019 2-4pm)");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }

    public void printTaskDeleted(int index, Tasks task) {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "Task " + index + ": " +
                task + " has been deleted!");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }
    public void printTaskInstruction(String type) {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "Please enter the " + type + "of your task:");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }
    public void printExceptionInstruction() {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "An error has occurred." +
                " Would you like to try again?");
        System.out.printf(FORMAT, "1. Yes");
        System.out.printf(FORMAT, "2. No");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }
    public void printErrorMessage() {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "Wrong command. Please follow instructions.");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }
    public void printClearErrorMessage() {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "An error has occurred in the clearing process.");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }
    public void printRepeatMessage() {
        System.out.printf(FORMAT, "This is a repeated task.");
    }

    /**
     * A respond message to a successful mark as done action.
     * The indexOfTask argument must be in the range of number of tasks
     * in the task list. The task argument must be the name of the task that
     * was marked as done.
     */
    public void printRespondToDoneTask(int indexOfTask, Tasks task) {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "Congrats! Task " + indexOfTask + ": " +
        task.getTask() + " has been completed");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }
    /**
     * A respond message to a successful add task action.
     * The task argument must be the name of the task that was added.
     */
    public void printRespondToAddTask(String task) {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "You have successfully added " + task);
        System.out.printf(FORMAT, "You have " + TaskList.getSize() +
                " task(s) now in total");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }
    /**
     * A respond message to a successful clear list action.
     * The list argument is the current task list in the myTask.txt file.
     */
    public void printRespondToClearTask(List<Tasks> list) throws IllegalClearException {
        if (list.isEmpty()) {
            System.out.println(SPLIT_UPPER_BOUNDARY);
            System.out.printf(FORMAT, "You have successfully cleared your task list!");
            System.out.println(SPLIT_LOWER_BOUNDARY);
        } else {
            throw new IllegalClearException();
        }
    }
}
