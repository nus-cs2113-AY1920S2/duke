package duke;

import duke.commands.Command;
import duke.commands.ExitCommand;

import java.io.*;

public class Duke {

    private static TaskList tasks = new TaskList();
    private static Parser parser = new Parser();

    public static void main(String[] args) {

        try {
            Storage.readFromFile(tasks);
        } catch (IOException e) {
            System.out.println("Creating save file...");
        }

        Ui.printWelcomeMessage();

        while (true) {

            Command command = parser.scanCommand();
            if (command == null) {
                continue;
            }
            if (command instanceof ExitCommand) {
                ((ExitCommand) command).Execute();
                break;
            }
            command.execute(tasks);
        }
    }
}
