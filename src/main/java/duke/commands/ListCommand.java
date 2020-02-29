package duke.commands;

import duke.storage.Storage;

import java.io.File;
import java.util.List;

/**
 * Represents an "list" command that user will input.
 * A <code>duke.commands.ListCommand</code> object will be executed when the User types in "list" in the UI
 * e.g., <code>list</code>
 */
public class ListCommand extends Command {

    /**
     * Executes the list command
     *
     * Prints the entire list
     *
     * @param myTasks The list where children command will store Tasks.
     * @param saveFile The place where children command will save Tasks.
     * @param commands The rest of the description that has not been parsed yet. e.g., "team meeting /at central lib"
     * @param command The actual command for the children. e.g., "deadline", "event".
     */
    public void execute(Storage myTasks, File saveFile, List<String> commands, String command) {
        myTasks.displayTasks();
    }
}
