package Duke.Ui;

import Duke.Task.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static Duke.Library.Message.*;

public class Ui {

    private Scanner sc;

    public Ui (){
        sc = new Scanner(System.in);
    }

    public static final void displayError(String Error){
        System.out.println(Error);
    }

    public static final void displayWelcomeMessage() {
        System.out.println("\n" + LINE_DIVIDER + MESSAGE_WELCOME + LINE_DIVIDER + BOT_LOGO + LINE_DIVIDER + BOT_DESC + LINE_DIVIDER);
    }

    public static final void displayAddedTask(Task task) {
        System.out.println(MESSAGE_ADD + task);
    }

    public static final void displayDeleteTask(Task task){
        System.out.println(MESSAGE_DELETE + task);
    }

    public static final void displayExitMessage() {
        System.out.println(MESSAGE_EXIT);
    }

    public static final void displayHelpMenu() {
        System.out.print(COMMAND_HELP_DESC);
    }

<<<<<<< HEAD
=======

>>>>>>> branch-A-JavaDoc
    public static final void displayNumberOfTasks(ArrayList TaskList) {
        System.out.print("\tNow you have " + TaskList.size() + " tasks in the list.\n");
    }

<<<<<<< HEAD
    public static void displaySearchResults(ArrayList TaskList) {
        if (!TaskList.isEmpty()) {
            for (int i = 0; i < TaskList.size(); i++) {
                System.out.print("\t");
                System.out.print(i + 1);
                System.out.print(". ");
                System.out.println(TaskList.get(i));
            }
            displayNumberOfTasksFound(TaskList);
        } else {
            System.out.println("\tEMPTY!!");
        }
    }

    private static void displayNumberOfTasksFound(ArrayList TaskList) {
        System.out.print("\tThere are " + TaskList.size() + " items found.\n");
    }

=======
>>>>>>> branch-A-JavaDoc
    public static void displayTaskList(ArrayList TaskList) {
        if (!TaskList.isEmpty()) {
            for (int i = 0; i < TaskList.size(); i++) {
                System.out.print("\t");
                System.out.print(i + 1);
                System.out.print(". ");
                System.out.println(TaskList.get(i));
            }
            displayNumberOfTasks(TaskList);
        } else {
            System.out.println("\tEMPTY!!");
        }
    }

    public String readCommand() {
        sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void displayMarkDone(Task task) {
        System.out.print(MESSAGE_MARK + task);
    }
}
