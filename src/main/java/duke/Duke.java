package duke;

import java.io.IOException;
import duke.command.Command;
import duke.command.ExitCommand;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private static TaskList tasks = new TaskList();
    private static Parser parser = new Parser();

    public static void main(String[] args) {
        try {
            Storage.insertFileContents(tasks);
        } catch (IOException e) {
            System.out.println("Creating a new file!");
        }

        Ui.printWelcomeMessage();

        while (true) {
            Command command = parser.getCommand();
            if (command == null) {
                continue;
            }
            if (ExitCommand.isExit(command)) {
                Ui.printByeMessage();
                break;
            }
            command.executeCommand(tasks);
        }
    }
}