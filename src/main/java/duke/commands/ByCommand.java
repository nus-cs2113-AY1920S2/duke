package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Class to represent by command
 */
public class ByCommand extends Command {
    public static final Pattern FORMAT = Pattern.compile("^by\\s+\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{1,2}:\\d{2}\\s*",
            Pattern.CASE_INSENSITIVE);
    public static final String KEYWORD = "by";
    public static final String EXAMPLE_USAGE = "by 24/6/2022 16:30";
    public static final String ERROR_MESSAGE = "Command needs to be in form: by d/M/yyyy H:mm";
    private String message;
    private LocalDateTime dateTime;

    public ByCommand(TaskList taskList, LocalDateTime dateTime) {
        super(taskList);
        this.dateTime = dateTime;
        this.message = "These are your tasks by " + this.dateTime + ":";
        this.isPersistentCommand = false;
    }

    /**
     * List all tasks that occur before target dateTime
     */
    public void execute() {
        String tasks = taskList.getTasksByFilter((Task t) -> t.getIsBy(dateTime));
        Ui.printPretty(message + tasks);
    }
}
