package duke.commands;

import duke.exceptions.BlankStringException;
import duke.exceptions.DukeExceptionHandler;
import duke.filemanager.FileSaver;
import duke.printer.Printer;
import duke.storage.Storage;
import duke.tasks.ToDo;

import java.io.File;
import java.util.List;

/**
 *  Represents a "to_do" command that user will input. (note: to_do is written to escape the highlight from todo)
 *  A <code>to_doCommand</code> object will be executed when the User types in "todo" in the UI
 *  e.g., <code>todo</code> read books
 */
public class ToDoCommand extends Command {

    /**
     * Executes the to_do command
     *
     * This method will first parse the description
     * Then stores into list and saves into file
     * If the user supplies the wrong format to this command error will be printed.
     *
     * @param myTasks The list where children command will store Tasks.
     * @param saveFile The place where children command will save Tasks.
     * @param commands The rest of the description that has not been parsed yet. e.g., "team meeting /at central lib"
     * @param command The actual command for the children. e.g., "deadline", "event".
     */
    public void execute(Storage myTasks, File saveFile, List<String> commands, String command) {
        try {
            String description = commands.get(TASK_DESCRIPTION);
            description = description.trim();

            DukeExceptionHandler.isBlank(description);

            ToDo toDoTask = new ToDo(description);
            myTasks.storeTasks(toDoTask);

            FileSaver.saveFile(saveFile, description);

            Printer.printConfirmationMessage(command, toDoTask);

        } catch (IndexOutOfBoundsException | BlankStringException e) {
            Printer.printEmptyDescriptionError(command);
            Printer.printHint(command);
        }
    }
}
