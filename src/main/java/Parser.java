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
     * Parse the command for an event task
     *
     * @param arr user's command
     * @return string of array split by '/at'
     */
    public static String[] parseEvent(String[] arr) {
        String arr2[] = arr[1].split("/at ", 2);
        return arr2;
    }

    /**
     * Parse the command for an deadline task
     *
     * @param arr user's command
     * @return string of array split by '/by'
     */
    public static String[] parseDeadline(String[] arr) {
        String arr2[] = arr[1].split("/by ", 2);
        return arr2;
    }
}
