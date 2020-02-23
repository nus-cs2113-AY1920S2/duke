package duke;

import java.io.File;
import java.util.List;

/**
 * Represents a "delete" command that user will input.
 * A <code>duke.DeleteCommand</code> object will be executed when the User types in "delete" in the UI
 * e.g., <code>delete</code> 5
 */
public class DeleteCommand extends Command {

    /**
     * Executes the delete command.
     * Deletes the task at Index that user specified.
     *
     * This method will first parse the index from @param commands.
     * Then it will delete the task at the index and save the changes to the save file
     * If the User specified index is out of bounds index or a non-number, error will be printed.
     *
     * @param myTasks The list where children command will store Tasks.
     * @param saveFile The place where children command will save Tasks.
     * @param commands The rest of the description that has not been parsed yet. e.g., "team meeting /at central lib"
     * @param command The actual command for the children. e.g., "deadline", "event".
     */
    public void execute(Storage myTasks, File saveFile, List<String> commands, String command) {
        try {
            String indexAsString = commands.get(LIST_INDEX);
            indexAsString = indexAsString.trim();

            int index = Integer.parseInt(indexAsString);
            Task taskDelete = myTasks.getTask(index);
            myTasks.deleteTask(index);

            FileSaver.deleteSpecificLine(saveFile, index);

            Printer.printConfirmationMessage(command, taskDelete);

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Printer.printError(); //TODO add custom error message when accessing OFB index
        }
    }
}
