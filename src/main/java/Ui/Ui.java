package Ui;

import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    Scanner myScanner;

    public Ui() {
        this.myScanner = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO + "What can I do for you?\n");
    }

    public void printGoodbyeMessage() {
        System.out.println("Exiting DUKE\n" + LOGO);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printHardDiskNotFound() {
        System.out.println("No existing stored data file found. Creating new file to store data!");
    }

    public void printPromptMessage() {
        System.out.print("Input a command:");
    }

    public String readCommand() {
        printPromptMessage();
        return myScanner.nextLine();
    }

    public void printDivider() {
        System.out.println("____________________________________________________________");
    }
}
