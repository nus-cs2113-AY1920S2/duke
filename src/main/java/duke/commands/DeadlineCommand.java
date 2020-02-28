package duke.commands;

import duke.tasklist.TaskList;
import duke.exceptions.BadLineFormatException;
import duke.tasks.Deadline;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Class for a deadline command that error checks user's input and can be executed to add the task to the
 * <code>TaskList</code>
 */
public class DeadlineCommand extends Command {
    public static final String EXAMPLE_USAGE = "deadline finish math homework /by 5/10/2021 16:30";
    public static final String KEYWORD = "deadline";
    private Deadline deadline;

    public DeadlineCommand(String keyword, String[] tokens, TaskList taskList) throws BadLineFormatException {
        super(keyword, tokens, taskList);
        if (!Arrays.asList(tokens).contains("/by")) {
            throw new BadLineFormatException("Input does not contain \" /by \"");
        }

        int byIndex = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("/by")) {
                byIndex = i;
                break;
            }
        }

        if (byIndex == tokens.length - 1) {
            throw new BadLineFormatException("Input does not contain a due date");
        } else if (byIndex == 1) {
            throw new BadLineFormatException("Input does not contain a description");
        }

        String description = String.join(" ", Arrays.copyOfRange(tokens, 1, byIndex));
        String dueDateTime = String.join(" ", Arrays.copyOfRange(tokens, byIndex + 1, tokens.length));

        try {
            deadline = new Deadline(description, dueDateTime);
        } catch (DateTimeParseException e) {
            throw new BadLineFormatException(e.getMessage());
        }
    }

    /**
     * Adds the deadline task to the referenced <code>TaskList</code>
     */
    public void execute() {
        taskList.addTask(deadline);
    }
}
