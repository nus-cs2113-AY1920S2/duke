package duke;

import java.io.IOException;

/**
 * runs Duke, the program to help you
 * keep track of the work that you need to do,
 * the deadlines that you need to meet and the
 * events that you need to be at.
 */
public class Duke {
    public static String inputLine = null; //most updated line of input
    public static boolean isEnd = false;

    public static void main(String[] args) {
        run("duke.txt");

    }


    /**
     * start the program
     *
     * @param filePath the path to file of offline list
     */
    private static void run(String filePath) {
        Ui.printWelcomeMessage();
        try {
            Storage.addExistingList(filePath); // Reads offline list into current list
        } catch (IOException e) {
            System.out.println(Constants.ioErrorMessage);
        }

        runCommandLoop(filePath);  // Reads in the first command from the user
        Ui.printExitMessage();
    }


    /**
     * Goes into loop
     */
    public static void runCommandLoop(String filePath) {
        while (!isEnd) {
            try {
                inputLine = Ui.readInput();
                Parser.determineCommand(filePath);
            } catch (IndexOutOfBoundsException e) {
                Ui.printErrMsg(Constants.errMissingParam);
            } catch (DukeException e) {
                Ui.printErrMsg(Constants.unknownCommandMessage);
            }
        }
    }
}
