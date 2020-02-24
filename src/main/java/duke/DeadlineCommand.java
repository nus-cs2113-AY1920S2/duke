package duke;

import java.io.File;
import java.util.List;

/**
 * Represents a "deadline" command that user will input.
 * A <code>duke.DeadlineCommand</code> object will be executed when the User types in "deadline" in the UI
 * e.g., <code>deadline</code> submit iP /by March 2
 */
public class DeadlineCommand extends Command {

    /**
     * Executes the deadline command.
     *
     * This method will first parse the description and deadline.
     * Then stores it into the list and save it to the file.
     * If User supplies the wrong format to the deadline command, error will be printed.
     *
     * @param myTasks The list to store the task.
     * @param saveFile The filepath to save the task.
     * @param commands The remaining descriptions that has not been parsed i.e. "submit iP /by March 2"
     * @param command The exact command that was supplied for this method i.e. "deadline"
     */
    public void execute(Storage myTasks, File saveFile, List<String> commands, String command) {
        try {
            String descriptionAndDeadline = commands.get(TASK_DESCRIPTION_AND_DATE);
            List<String> separated = Parser.parseDeadlineDate(descriptionAndDeadline);

            String description = separated.get(TASK_DESCRIPTION);
            String deadline = separated.get(TASK_DEADLINE);
            description = description.trim();
            deadline = deadline.trim();

            DukeExceptionHandler.isBlank(description);
            DukeExceptionHandler.isBlank(deadline);

            Deadline deadlineTask = new Deadline(description, deadline);
            myTasks.storeTasks(deadlineTask);

            FileSaver.saveFile(command, saveFile, description, deadline);

            Printer.printConfirmationMessage(command, deadlineTask);

        } catch (IndexOutOfBoundsException | BlankStringException e) {
            Printer.printFormatError(command);
            Printer.printHint(command);
        }
    }
}
