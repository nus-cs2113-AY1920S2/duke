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

        TaskList taskList = new TaskList().invoke();
        ArrayList<Task> taskArrayList = taskList.getTaskArrayList();
        ArrayList<Task> lastShownList = taskList.getLastShownList();
        int taskListSize = taskList.getTaskListSize();

        try {
            taskListSize = Storage.loadFileContents(FILEPATH, taskArrayList);
            Ui.printTasks(taskArrayList, taskListSize);
        } catch (FileNotFoundException e) {
            System.out.println("No saved file available");
        }

        Scanner scanner = new Scanner(System.in);

        Parser.parseUserCommands(taskArrayList, lastShownList, taskListSize, scanner);
    }
}
