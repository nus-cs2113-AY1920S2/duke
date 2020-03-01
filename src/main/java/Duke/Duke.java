package Duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Represents the Duke task manager.
 */
public class Duke {

    public static final String GREETING =
            "\n" +
            "  ___       _       \n" +
            " |   \\ _  _| |_____ \n" +
            " | |) | || | / / -_)\n" +
            " |___/ \\_,_|_\\_\\___|\n" +
            "                    \n" +
            "Hello! I'm Duke\n" + "What can I do for you?\n" +
            "____________________________________________________________\n";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";
    public static final String FILEPATH = "data.txt";

    public static void main(String[] args) {
        System.out.println(GREETING);

        File f = new File(FILEPATH);

        TaskList taskArray = new TaskList();

        try {
            Storage.loadFileContents(FILEPATH, taskArray);
            Ui.printTasks(taskArray);
        } catch (FileNotFoundException e) {
            System.out.println("No saved file available");
        }

        Scanner scanner = new Scanner(System.in);

        Parser.parseUserCommands(taskArray, scanner);
    }
}
