import java.io.File;
import java.util.List;

public class DeadlineCommand extends Command {

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
