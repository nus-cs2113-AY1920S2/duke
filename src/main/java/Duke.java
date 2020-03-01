import Tasks.TaskList;
import command.Controller;
import command.Storage;
import command.Ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private static int todoSize = 0;

    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        String userInput;
        TaskList taskListArrayList = new TaskList();
        Storage storage = new Storage();
        Controller controller = new Controller();

        try {
            taskListArrayList = storage.load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        do {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            userInput = userInput.trim();
            System.out.println(controller.readInput(userInput, taskListArrayList));
            storage.save(taskListArrayList);
        } while (!userInput.equalsIgnoreCase("bye"));

        Ui.printBye();
    }
}
