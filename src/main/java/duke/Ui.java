package duke;

import java.util.Scanner;

public class Ui {

    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void startMessage() {
        String logo = ".______     ______   .______   \n"
                + "|   _  \\   /  __  \\  |   _  \\  \n"
                + "|  |_)  | |  |  |  | |  |_)  | \n"
                + "|   _  <  |  |  |  | |   _  <  \n"
                + "|  |_)  | |  `--'  | |  |_)  | \n"
                + "|______/   \\______/  |______/  \n";
        String welcomeMessage = "Hello human! I am Bob.\n" + "What can I do for you?";

        System.out.println("Bob the chatbot\n" + logo);
        System.out.println(welcomeMessage);
        System.out.println("____________________________________________________________________");
    }

    public void endMessage() {
        System.out.println("____________________________________________________________________");
        System.out.println("Bob thanks you for coming! See you again soon!");
    }

    public String readCommand() {
        return (sc.nextLine());
    }

    public void displayErrorMessage(String err) {
        System.out.println(err);
    }

    public void displayDividerLine() {
        System.out.println("____________________________________________________________________");
    }


}
