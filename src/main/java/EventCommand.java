import java.io.File;
import java.util.List;

public class EventCommand extends Command {

    public void execute(Storage myTasks, File saveFile, List<String> commands, String command) {
        try {
            String descriptionAndEventAt = commands.get(TASK_DESCRIPTION_AND_DATE);
            List<String> separated = Parser.parseEventAt(descriptionAndEventAt);

            String description = separated.get(TASK_DESCRIPTION);
            String eventAt = separated.get(TASK_EVENT_AT);

            description = description.trim();
            eventAt = eventAt.trim();

            DukeExceptionHandler.isBlank(description);
            DukeExceptionHandler.isBlank(eventAt);

            Event eventTask = new Event(description, eventAt);
            myTasks.storeTasks(eventTask);

            FileSaver.saveFile(command, saveFile, description, eventAt);

            Printer.printConfirmationMessage(command, eventTask);

        } catch (IndexOutOfBoundsException | BlankStringException e) {
            Printer.printFormatError(command);
            Printer.printHint(command);
        }
    }
}
