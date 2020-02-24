package duke.commands;

import duke.filemanager.FileSaver;
import duke.printer.Printer;
import duke.storage.Storage;
import duke.tasks.Task;

import java.io.File;
import java.util.List;

/**
 * Represents a "done" command that user will input.
 * A <code>duke.commands.DoneCommand</code> object will be executed when the User types in "done" in the UI
 * e.g., <code>done</code> 5
 */
public class DoneCommand extends Command {

    /**
     * Executes the done command.
     * Marks the task as done at index that User specified.
     *
     * This method will first parse the index from @param commands
     * Then marks the task at the specified index as done.
     * Then save the changes to file.
     * If the User specified out of bounds index or a non-number, error will be printed.
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
            Task taskDone = myTasks.getTask(index);
            taskDone.markAsDone(); //TODO QUESTION: How come.. this works?.. is it because its static?

            FileSaver.updateFile(saveFile, index);

            Printer.printConfirmationMessage(command, taskDone);

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Printer.printFormatError(command);
            Printer.printHint(command);
        }
    }
}
