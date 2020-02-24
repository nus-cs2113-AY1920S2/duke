package duke.commands;

import duke.exceptions.BlankStringException;
import duke.exceptions.DukeExceptionHandler;
import duke.printer.Printer;
import duke.storage.Storage;
import duke.tasks.Task;

import java.io.File;
import java.util.List;

public class FindCommand extends Command {

    public void execute(Storage myTasks, File saveFile, List<String> commands, String command) {
        try {
            String keyword = commands.get(TASK_DESCRIPTION);
            keyword = keyword.trim();

            DukeExceptionHandler.isBlank(keyword);

            List<Task> foundTasks = myTasks.findTasks(keyword);
            Printer.printTasks(command, foundTasks);

        } catch (BlankStringException | IndexOutOfBoundsException e) {
            Printer.printEmptyDescriptionError(command);
            Printer.printHint(command);
        }
    }
}
