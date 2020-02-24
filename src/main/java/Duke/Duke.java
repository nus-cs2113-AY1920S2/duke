package Duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {

    public static final String GREETING = "Hello! I'm Duke\n" + "What can I do for you?";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";
    public static final String FILEPATH = "saved/data.txt";

    public static void main(String[] args) {
        System.out.println(GREETING);

        File f = new File(FILEPATH);

        ArrayList<Task> taskArrayList = new ArrayList<>();
        int taskListSize = 0;

        try {
            taskListSize = Storage.loadFileContents(FILEPATH, taskArrayList);
            Ui.printTasks(taskArrayList, taskListSize);
        } catch (FileNotFoundException e) {
            System.out.println("No saved file available");
        }
        ArrayList<Task> lastShownList = (ArrayList<Task>) taskArrayList.clone();

        Scanner scanner = new Scanner(System.in);

        Parser.parseUserCommands(taskArrayList, lastShownList, taskListSize, scanner);
    }
}
