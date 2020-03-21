package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles user input and messages shown to user of this application.
 */
public class Ui {

    public static final String BOT_NAME = "E.D.I.T.H.";
    public static final String BOT_LOGO = "\n"
            + " _______         ______           _________        __________          __     __                \n"
            + "|   ____|       |    _  |         |__    __|      |___    ___|        |  |   |  |               \n"
            + "|   |___        |   | |  |           |  |             |  |            |  |___|  |               \n"
            + "|    ___|       |   | |  |           |  |             |  |            |   ___   |               \n"
            + "|   |___    _   |   |_|  |   _     __|  |__     _     |  |       _    |  |   |  |    _          \n"
            + "|_______|  |_|  |_______/   |_|   |________|   |_|    |__|      |_|   |__|   |__|   |_|         \n";
    public static final String BOT_DESC = "\n"
            + "\tHi There! My Name is E.D.I.T.H, your personal assistant\n";
    public static final String MESSAGE_WELCOME = "\n\tHello! I'm " + BOT_NAME + "\n\tWhat can I do for you?";
    public static final String MESSAGE_EXIT = "\tBye. Hope to see you again soon!";
    public static final String MESSAGE_ADD = "\tGot it. I've added this ";
    public static final String MESSAGE_MARK = "\tYou have marked -- ";
    public static final String MESSAGE_DELETE = "\tYou have deleted --";
    public static final String LINE_DIVIDER = "\n"
            + "\t___________________________________________________________________";
    public static final String MESSAGE_HELP_DESC = "\tFor more information, please read the user guide!";

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static final void displayError(String error) {
        System.out.println(error);
    }

    public static final void displayWelcomeMessage() {
        System.out.println("\n" + LINE_DIVIDER + MESSAGE_WELCOME
                + LINE_DIVIDER + BOT_LOGO + LINE_DIVIDER + BOT_DESC + LINE_DIVIDER);
    }

    public static final void displayAddedTask(Task task) {
        System.out.println(MESSAGE_ADD + task);
    }

    public static final void displayDeletedTask(Task task) {
        System.out.println(MESSAGE_DELETE + task);
    }

    public static final void displayExitMessage() {
        System.out.println(MESSAGE_EXIT);
    }

    public static final void displayMarkDone(Task task) {
        System.out.println(MESSAGE_MARK + task);
    }

    public static final void displayHelpMenu() {
        System.out.println(MESSAGE_HELP_DESC);
    }

    public static final void displayNumberOfTasks(ArrayList tasklist) {
        System.out.print("\tNow you have " + tasklist.size() + " tasks in the list.\n");
    }

    public void displayDone(Task task) {
        System.out.println("It is already marked!");
    }

    /**
     * Display tasks that match the keyword.
     *
     * @param tasklist The tasklist that contains tasks that match the keyword.
     */

    public static void displaySearchResults(ArrayList tasklist) {
        if (!tasklist.isEmpty()) {
            for (int i = 0; i < tasklist.size(); i++) {
                System.out.print("\t");
                System.out.print(i + 1);
                System.out.print(". ");
                System.out.println(tasklist.get(i));
            }
            displayNumberOfTasksFound(tasklist);
        } else {
            System.out.println("\tItem not found!!!");
        }
    }

    /**
     * Display the number of tasks.
     *
     * @param tasklist The existing tasklist.
     */
    private static void displayNumberOfTasksFound(ArrayList tasklist) {
        System.out.print("\tThere are " + tasklist.size() + " items found.\n");
    }

    /**
     * Display the existing tasks.
     *
     * @param tasklist The existing tasklist.
     */
    public static void displayTaskList(ArrayList tasklist) {
        if (!tasklist.isEmpty()) {
            for (int i = 0; i < tasklist.size(); i++) {
                System.out.print("\t");
                System.out.print(i + 1);
                System.out.print(". ");
                System.out.println(tasklist.get(i));
            }
            displayNumberOfTasks(tasklist);
        } else {
            System.out.println("\tEMPTY!!");
        }
    }

    public String readCommand() {
        sc = new Scanner(System.in);
        return sc.nextLine().strip();
    }
}
