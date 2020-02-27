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

    public static final void displayAddTask(Task task) {
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

    public static final void displayLineDivider() {
        System.out.println(LINE_DIVIDER);
    }

    public static final void displayNumberOfTasks(ArrayList TaskList) {
        System.out.print("\tNow you have " + TaskList.size() + " tasks in the list.\n");
    }

    public static void echoUserCommand(String userCommand) {
        System.out.println("\t[Command entered: " + userCommand + "]");
    }



    public static void exitProgram() {
        displayExitMessage();
    }

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
