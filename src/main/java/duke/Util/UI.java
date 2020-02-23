package duke.Util;

import duke.exceptions.IllegalClearException;
import duke.taskmanager.Tasks;

import java.util.List;
import java.util.Scanner;

public class UI {
    private static Scanner userInput = new Scanner(System.in);
    private final String format = "0O=-             %-60s-=O0%n";

    private final String split = "=============================" +
            "====================================================";

    private final String blankLine = "0O=-                      " +
            "                                                   -=O0";

    private final String splitUpperBoundary = split +"\n000000000000000" +
            "00000000000000000000000000000000000000000000000000000000000" +
            "0000000\n" + blankLine;

    private final String splitLowerBoundary = blankLine + "\n0000000" +
            "00000000000000000000000000000000000000000000000000000000" +
            "000000000000000000\n" + split;

    public UI(){
    }

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
        System.out.println(splitUpperBoundary);
        System.out.printf(format, "Please select the task that you have ");
        System.out.printf(format, "    completed (select the no)");
        TaskList.showList();
    }
    public void printDeleteIntro() {
        System.out.println(splitUpperBoundary);
        System.out.printf(format, "Please select the task that you wish ");
        System.out.printf(format, "    to delete (select the no)");
        TaskList.showList();
    }
    public void printExit() {
        System.out.println(splitUpperBoundary);
        System.out.printf(format, "Bye. Hope to see you again soon!");
        System.out.println(splitLowerBoundary);
    }

    public void printExeType() {
        System.out.println(splitUpperBoundary);
        System.out.printf(format, "Hi what can I do for you? " +
                "(please key in the number):" );
        System.out.printf(format, "1. Add a new task,");
        System.out.printf(format, "2. Show my tasks,");
        System.out.printf(format, "3. I've completed my task!");
        System.out.printf(format, "4. Delete a task");
        System.out.printf(format, "5. Find a task");
        System.out.printf(format, "6. Clear all tasks, or");
        System.out.printf(format, "7. See you next time!");
        System.out.println(splitLowerBoundary);
    }

    public void printTaskType() {
        String format = "0O=-    %-69s-=O0%n";
        System.out.println(splitUpperBoundary);
        System.out.printf(format, "Which category does your task belong to?");
        System.out.printf(format, "1. ToDos: tasks without any date/time attached to it ");
        System.out.printf(format, "   (e.g., visit new theme park)\n");
        System.out.printf(format, "2. Deadlines: tasks that need to be done before a specific");
        System.out.printf(format, "   date/time (e.g., submit report by 11/10/2019 5pm)\n");
        System.out.printf(format, "3. Events: tasks that start at a specific time and ends");
        System.out.printf(format, "   at a specific time(e.g., team project meeting on 2/10/2019 2-4pm)");
        System.out.println(splitLowerBoundary);
    }

    public void printTaskDeleted(int index, Tasks task) {
        System.out.println(splitUpperBoundary);
        System.out.printf(format, "Task " + index + ": " +
                task + " has been deleted!");
        System.out.println(splitLowerBoundary);
    }
    public void printTaskInstruction(String type) {
        System.out.println(splitUpperBoundary);
        System.out.printf(format, "Please enter the " + type + "of your task:");
        System.out.println(splitLowerBoundary);
    }
    public void printExceptionInstruction() {
        System.out.println(splitUpperBoundary);
        System.out.printf(format, "An error has occurred." +
                " Would you like to try again?");
        System.out.printf(format, "1. Yes");
        System.out.printf(format, "2. No");
        System.out.println(splitLowerBoundary);
    }
    public void printErrorMessage() {
        System.out.println(splitUpperBoundary);
        System.out.printf(format, "Wrong command. Please follow instructions.");
        System.out.println(split);
    }
    public void printClearErrorMessage() {
        System.out.println(splitUpperBoundary);
        System.out.printf(format, "An error has occurred in the clearing process.");
        System.out.println(splitLowerBoundary);
    }
    public void printRepeatMessage() {
        System.out.printf(format, "This is a repeated task.");
    }

    public void printRespondToDoneTask(int indexOfTask, Tasks task) {
        System.out.println(splitUpperBoundary);
        System.out.printf(format, "Congrats! Task " + indexOfTask + ": " +
        task.getTask() + " has been completed");
        System.out.println(splitLowerBoundary);
    }
    public void printRespondToAddTask(String task) {
        System.out.println(splitUpperBoundary);
        System.out.printf(format, "You have successfully added " + task);
        System.out.printf(format, "You have " + TaskList.getSize() +
                " task(s) now in total");
        System.out.println(splitLowerBoundary);
    }
    public void printRespondToClearTask(List<Tasks> list) throws IllegalClearException {
        if (list.isEmpty()) {
            System.out.println(splitUpperBoundary);
            System.out.printf(format, "You have successfully cleared your task list!");
            System.out.println(splitLowerBoundary);
        } else {
            throw new IllegalClearException();
        }
    }
}
