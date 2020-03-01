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
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Shows welcome message at the beginning and ask for users' command.
     */
    public static void showWelcomeMessage() {
        showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("If you are a new user. Please type 'help' to view how to give a  correct command.");
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
     * Prints out help message to show users the format of all kinds of commands.
     */
    public static void showHelpMessage(){
        System.out.println("There are 10 types of commands；todo, deadline, event, " +
                "list, find, show, done, delete, help and exit");
        System.out.println("Followings are examples of commands:");
        System.out.println("todo : todo borrow books");
        System.out.println("deadline : deadline return books /by 2020-03-02T11:00:00");
        System.out.println("event : event group meeting /at 2020-03-03T14:00:00");
        System.out.println("list: list");
        System.out.println("find: find books");
        System.out.println("show : show 2020-03-02");
        System.out.println("done : done 2");
        System.out.println("delete : delete 1");
        System.out.println("help : help");
        System.out.println("exit : bye");
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
        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
