package duke;

import java.util.Scanner;

/**
 * Represents the user interface that interacts with user
 */
public class Ui {

    /**
     * Returns a string that captures the entire user input
     *
     * @return entire user input
     */
    public static String readFromUser() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
