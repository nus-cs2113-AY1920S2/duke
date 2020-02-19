package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner input;
    public Ui (Scanner input) {
        this.input = input;
    }
    public static void showWelcomeMessage() {
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
    }
    public String getUserCommand() {
        return input.nextLine();
    }
    public static void showLoadingError() {
        System.out.println("File Not Found");
    }
}
