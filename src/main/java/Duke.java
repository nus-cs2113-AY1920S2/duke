import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Project Duke.
 * Keeps a record of the tasks that the user has, which can be saved into and loaded from a hard disk.
 */

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();
    static TextUi textUi = new TextUi();

    /**
     * Contains all the necessary objects to keep track of the tasks.
     * @param args
     */
    public static void main(String[] args) {
        textUi.printWelcomeMessage();
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        while (!userInput.equals("bye")) {
            try {
                Parser.parseInput(userInput);
            } catch (IndexOutOfBoundsException exception) {
                textUi.printIndexOutOfBoundsExceptionMessage(exception);
            } catch (IllegalArgumentException exception) {
                textUi.printIllegalArgumentExceptionMessage(exception);
            }
            userInput = scan.nextLine();
        }
        textUi.printExitMessage();
    }
}
