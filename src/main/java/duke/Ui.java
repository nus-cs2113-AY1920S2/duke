package duke;

import java.util.Scanner;

/**
 * Ui class for reading user input and displaying greeting message.
 */
public class Ui {

    Scanner scan;

    /**
     * Constructor for Ui class.
     * Instantiate new Scanner object when reading user inputs.
     */
    public Ui() {
        this.scan = new Scanner(System.in);
    }


    /**
     * Prints the greeting message of Duke.
     */
    public void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke\n");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Reads user input from command line.
     * @return String of user input.
     */
    public String readUserData() {
        return (scan.nextLine());
    }

    public void errorMessage(String err) {
        System.out.println(err);
    }

}
