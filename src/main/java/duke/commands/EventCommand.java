package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Event;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Class for an event command that error checks user's input and can be executed to add the task to the
 * <code>TaskList</code>
 */
public class EventCommand extends Command {
    public static final Pattern FORMAT = Pattern.compile("^event\\s+(\\w\\s*)+\\s/at\\s+" +
                    "\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{1,2}:\\d{2}\\s*", Pattern.CASE_INSENSITIVE);
    public static final String EXAMPLE_USAGE = "event math class /at 31/7/2020 8:30";
    public static final String ERROR_MESSAGE = "Command needs to be in form: event <description> /at dd/mm/yyyy hh:mm";
    public static final String KEYWORD = "event";
    private Event event;

    /**
     * @param taskList the <code>TaskList</code>
     * @param description the description
     * @param dateTime the dateTime
     */
    public EventCommand(TaskList taskList, String description, LocalDateTime dateTime) {
        super(taskList);
        this.event = new Event(description, dateTime, false);
    }

    /**
     * Adds the event task to the referenced <code>TaskList</code>
     */
    public void execute() {
        taskList.addTask(event);
    }
}
