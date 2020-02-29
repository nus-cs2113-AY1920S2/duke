package duke;

import duke.commands.Command;
import duke.commands.ExitCommand;

import java.io.*;

public class Duke {

    private static TaskList tasks = new TaskList();
    private static Parser parser = new Parser();

    public static void main(String[] args) {

        readFromFile();
        Ui.printWelcomeMessage();
        executeCommands();
    }

    /**
     * Parses the user input and executes the commands inputted
     * by the user.
     */
    private static void executeCommands() {
        while (true) {
            Command command = parser.scanCommand();
            if (command == null) {
                continue;
            } else if (command instanceof ExitCommand) {
                ((ExitCommand) command).Execute();
                break;
            } else {
                command.execute(tasks);
            }
        }
    }

    /**
     * Reads the list of tasks from the save file upon start up.
     */
    private static void readFromFile() {
        try {
            Storage.readFromFile(tasks);
        } catch (IOException e) {
            // Save file does not exist, creating one in the same folder
            System.out.println("Creating save file...");
        }
    }
}
