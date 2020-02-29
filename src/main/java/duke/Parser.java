package duke;

import java.util.Scanner;

/**
 * Makes sense of all user input for Duke
 */
public class Parser {

    /**
     * Get input string
     * @return input string
     */
    static String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Get command word
     * @param input input string
     * @return command word
     */
    static String getCommandWord(String input) {
        String[] splitTask = input.split(" ");
        return splitTask[0];
    }

    /**
     * Get description that follows command word
     * @param input input string
     * @return description that follows command word
     */
    static String getDescription(String input) {
        String[] splitTask = input.split(" ");
        String task = "";
        for (int i = 1; i < splitTask.length; i++) {
            task += " " + splitTask[i];
        }
        return task;
    }

}
