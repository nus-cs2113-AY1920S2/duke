import java.util.Scanner;

public class Parser {

    /**
     * Read input and split it by spaces
     *
     * @param in Scanner object
     * @return the parsed input
     */
    public static String[] readInput(Scanner in) {
        String s = in.nextLine();
        String arr[] = s.split(" ", 2);
        return arr;
    }

    /**
     * Get the user's command
     *
     * @param arr User's input split into an array of strings
     * @return the command executed by the user
     */
    public static String getCommand(String[] arr) {
        return arr[0];
    }
}
