package duke;

import java.util.Scanner;

public class Ui {

    private Scanner read=new Scanner(System.in);
    private String command;

    public static void printDividingLine() {
        System.out.println("------------***------------");
    }

    static void exitMessage() {
        printDividingLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDividingLine();
    }

    static void welcomeMessage() {
        printDividingLine();
        System.out.println("Hello! I'm Momo");
        System.out.println("What can I do for you?");
        printDividingLine();
    }

    public void getNextCommand(){
        command=read.nextLine();
    }

    public void showLoadingError(){
        System.out.println("There is something wrong during the loading process.");
    }

    public String getCommand() {
        return command;
    }
}
