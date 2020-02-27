package duke;

import duke.task.Task;


import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    public static ArrayList<Task> listOfTasks = new ArrayList<>(); // array of tasks that is <=100
    public static int sizeOfList = 0; // number of items in the list
    public static String inputLine = null; //most updated line of input


    public static void main(String[] args) {
        run("duke.txt");

    }

    private static void run(String filePath) {
        Ui.printWelcomeMessage();
        try {
            Storage.addExistingList(filePath); // Reads offline list into current list
        } catch (IOException e) {
            System.out.println(Constants.ioErrorMessage);
        }

        runCommandLoop();  // Reads in the first command from the user
        Storage.saveListOffline(filePath); // save current list to be offline
        Ui.printExitMessage();
    }


    /**
     * Goes into loop
     */
    public static void runCommandLoop() {
        boolean isEnd = false;

        while (!isEnd) {
            try {
                inputLine = Ui.readInput();
                if (inputLine.equals("bye")) {
                    isEnd = true;
                } else {
                    Parser.determineCommand();
                }

            } catch (IndexOutOfBoundsException e) {
                Ui.printErrMsg(Constants.errMissingParam);
            } catch (DukeException e) {
                Ui.printErrMsg(Constants.unknownCommandMessage);
            }
        }
    }


}
