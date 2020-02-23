package duke;

import java.io.File;
import java.util.List;

/**
 *  Represents a "bye" command that user will input.
 *  A <code>duke.ByeCommand</code> object will be executed when the User types in "bye" in the UI
 *  e.g., <code>bye</code>
 */
public class ByeCommand extends Command {

    public void execute(Storage myTasks, File saveFile, List<String> commands, String command) {
        Printer.printLines();
        Printer.printIndentation();
        Printer.printBye();
        Printer.printLines();

        System.exit(0);
    }
}
