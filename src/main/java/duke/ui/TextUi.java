package duke.ui;

import duke.common.Messages;
import duke.data.task.Task;
import duke.data.task.TaskList;

import java.util.Scanner;

import static duke.common.Messages.*;

public class TextUi {

    /**
     * A platform independent line separator.
     */
    private static final String LS = System.lineSeparator();

    private Scanner in = new Scanner(System.in);

    public TextUi() {
    }

    public static void printEmptyList() {
        System.out.println(Messages.MESSAGE_EMPTY_LIST);
    }

    public static void printList(TaskList taskList) {
        System.out.println(MESSAGE_TASKS_LIST);
        int taskCounter = taskList.size();
        for (int i = 0; i < taskCounter; i++) {
            System.out.println("    " + (i + 1) + "." + taskList.get(i));
        }
    }

    public static void printExit() {
        System.out.println(Messages.MESSAGE_GOODBYE);
    }

    public static void printDone(TaskList tasklist, int index) {
        System.out.println(Messages.MESSAGE_TASK_DONE);
        System.out.println(Messages.SPACER + tasklist.get(index));
    }

    public static void printDelete(TaskList tasklist, int index) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + tasklist.get(index));
    }

    public static void printRemaining(int taskCounter) {
        System.out.println("    Now you have " + taskCounter + " task(s) in the list.");
    }

    public static void printTask(Task task, int taskCounter) {
        System.out.println("    Got it! I've added this task:");
        System.out.println("       " + task);
        taskCounter++;
        System.out.println("    Now you have " + taskCounter + " task(s) in the list.");
    }

    public static void printKeyNotFound(String description) {
        System.out.println("    " + description + MESSAGE_KEY_NOT_FOUND);
    }

    public static void printTaskFound(String description, TaskList tasklist) {
        int counter = 1;
        System.out.println(Messages.MESSAGE_TASK_FOUND);
        for (Task task : tasklist) {
            if (task.getDescription().contains(description)) {
                System.out.println("       " + counter + "." + task);
                counter++;
            }
        }
    }

    public void showWelcome() {
        String logo = MESSAGE_WELCOME;
        System.out.println(DIVIDER);
        System.out.print(logo);
        System.out.println(DIVIDER);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public String readCommand() {
        return in.nextLine();
    }
}
