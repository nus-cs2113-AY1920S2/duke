import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Duke is a project that keeps a list of the tasks that need to be completed.
 */

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Stores any methods that require interaction with the user.
     */

    static TextUi textUi = new TextUi();

    /**
     * Contains all the objects needed to run the project.
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
