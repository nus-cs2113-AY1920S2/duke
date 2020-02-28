package duke.commands;

import duke.tasklist.TaskList;
import duke.exceptions.BadLineFormatException;
import duke.tasks.Event;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Class for an event command that error checks user's input and can be executed to add the task to the
 * <code>TaskList</code>
 */
public class EventCommand extends Command {
    public static final String EXAMPLE_USAGE = "event math class /at 31/7/2020 8:30";
    public static final String KEYWORD = "event";
    private Event event;

    public EventCommand(String keyword, String[] tokens, TaskList taskList) throws BadLineFormatException {
        super(keyword, tokens, taskList);
        if (!Arrays.asList(tokens).contains("/at")) {
            throw new BadLineFormatException("Input does not contain \" /at \"");
        }

        int atIndex = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("/at")) {
                atIndex = i;
                break;
            }
        }

        if (atIndex == tokens.length - 1) {
            throw new BadLineFormatException("Input does not contain a date/time");
        } else if (atIndex == 1) {
            throw new BadLineFormatException("Input does not contain a description");
        }

        String description = String.join(" ", Arrays.copyOfRange(tokens, 1, atIndex));
        String startEndDateTime = String.join(" ", Arrays.copyOfRange(tokens, atIndex + 1, tokens.length));

        try {
            event = new Event(description, startEndDateTime);
        } catch (DateTimeParseException e) {
            throw new BadLineFormatException(e.getMessage());
        }
    }

    /**
     * Adds the event task to the referenced <code>TaskList</code>
     */
    public void execute() {
        taskList.addTask(event);
    }
}
