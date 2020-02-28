package duke;

import java.util.Scanner;

public class Ui {

    private Scanner read=new Scanner(System.in);
    private String command;

    public static void showLine() {
        System.out.println("------------***------------");
    }

    public static void exitMessage() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    static void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Momo");
        System.out.println("What can I do for you?");
        showLine();
    }

    public String readCommand(){
        command=read.nextLine();
        return command;
    }

    public void showLoadingError(){
        System.out.println("There is something wrong during the loading process.");
    }

    public static void showError() {
        System.out.println("The command given is not acceptable.");
    }
}
