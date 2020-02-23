package duke;

import java.io.File;
import java.util.List;

public class FindCommand extends Command {

    public void execute(Storage myTasks, File saveFile, List<String> commands, String command) {
        try {
            String description = commands.get(TASK_DESCRIPTION);
            description = description.trim();

            DukeExceptionHandler.isBlank(description);

            List<Task> foundTasks = myTasks.findTasks(description);
            Printer.printTasks(command, foundTasks);

        } catch (BlankStringException | IndexOutOfBoundsException e) {
            Printer.printEmptyDescriptionError(command);
            Printer.printHint(command);
        }
    }
}
