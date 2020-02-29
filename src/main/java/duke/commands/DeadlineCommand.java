package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Deadline;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Class for a deadline command that error checks user's input and can be executed to add the task to the
 * <code>TaskList</code>
 */
public class DeadlineCommand extends Command {
    public static final Pattern FORMAT = Pattern.compile("^deadline\\s+(\\w\\s*)+\\s/by\\s+\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{1,2}:\\d{2}\\s*",
            Pattern.CASE_INSENSITIVE);
    public static final String EXAMPLE_USAGE = "deadline finish math homework /by 5/10/2021 16:30";
    public static final String ERROR_MESSAGE = "Command needs to be in form: deadline <description> /by dd/mm/yyyy hh:mm";
    public static final String KEYWORD = "deadline";
    private Deadline deadline;

    public DeadlineCommand(TaskList taskList, String description, LocalDateTime dateTime) {
        super(taskList);
        this.deadline = new Deadline(description, dateTime, false);
    }

    /**
     * Adds the deadline task to the referenced <code>TaskList</code>
     */
    public void execute() {
        taskList.addTask(deadline);
    }
}
