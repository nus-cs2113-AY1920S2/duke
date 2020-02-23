import java.io.File;
import java.util.List;

public class ToDoCommand extends Command {

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
