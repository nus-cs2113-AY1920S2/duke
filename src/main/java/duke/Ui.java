package duke;

import java.util.Scanner;

import static duke.Duke.BYE_MESSAGE;

public class Ui {
    public Ui() {

    }

    public void showWelcome() {
        String greeting = Duke.DIVIDER + Duke.HELLO_MESSAGE + Duke.HELP_MESSAGE + Duke.DIVIDER;
        System.out.println(greeting);
    }

    public void showLine() {
        System.out.print(Duke.DIVIDER);
    }

    public String readCommand() {
        Scanner commandScanner = new Scanner(System.in);
        String command = commandScanner.nextLine();
        return command;
    }

    public void showLoadingError() {
        System.out.println("\tThe directory does not exist, currently creating a new one");
    }

    public void showBye() {
        System.out.println(BYE_MESSAGE);
    }

    public String showError(String errorMessage) {
        return Duke.DIVIDER
                + ("☹ OOPS!!! I'm sorry, but there is an error :-(")
                + (errorMessage)
                + Duke.DIVIDER;
    }
}
