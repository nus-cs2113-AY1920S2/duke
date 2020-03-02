import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

/**
<<<<<<< HEAD
 * Duke is a project that keeps a list of the tasks that need to be completed.
=======
 * Project Duke.
 * Keeps a record of the tasks that the user has, which can be saved into and loaded from a hard disk.
>>>>>>> JavaDocs
 */

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Stores any methods that require interaction with the user.
     */

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
