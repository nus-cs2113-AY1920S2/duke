import java.io.File;
import java.util.List;

/**
 * Represents an "event" command that user will input.
 * A <code>EventCommand</code> object will be executed when the User types in "event" in the UI
 * e.g., <code>event</code> team meeting /at NUS
 */
public class EventCommand extends Command {

    /**
     * Executes the event command
     *
     * The method will first parse the description and place.
     * Then stores it in a list and the save file.
     * If User supplies the wrong format to the event command, error will be printed.
     *
     * @param myTasks The list where children command will store Tasks.
     * @param saveFile The place where children command will save Tasks.
     * @param commands The rest of the description that has not been parsed yet. e.g., "team meeting /at central lib"
     * @param command The actual command for the children. e.g., "deadline", "event".
     */
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
