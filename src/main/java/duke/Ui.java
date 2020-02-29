package duke;

import java.util.Scanner;

/**
 *  Ui is a object which is responsible for interacting with the users.
 *  A Ui object correspondents a scanner which get commands from users.
 */
public class Ui {

    private Scanner read=new Scanner(System.in);
    private String command;

    /**
     * Shows dividing line to make the output more readable.
     */
    public static void showLine() {
        System.out.println("------------***------------");
    }

    /**
     * Shows exit message when users type "bye", the exit command.
     */
    public static void exitMessage() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Shows welcome message at the beginning and ask for users' command.
     */
    public static void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Momo");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Reads a command from user.
     *
     * @return current command.
     */
    public String readCommand(){
        command=read.nextLine();
        return command;
    }

    /**
     * An error message which will come out when there is some problem in the process of loading.
     */
    public void showLoadingError(){
        System.out.println("There is something wrong during the loading process.");
    }

    /**
     * An error message that reminds the users the command is not acceptable by Duke.
     */
    public static void showError() {
        System.out.println("The command given is not acceptable.");
    }
}
