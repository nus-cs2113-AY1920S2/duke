package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Class for on command
 */
public class OnCommand extends Command {
    public static final Pattern FORMAT = Pattern.compile("^on\\s+\\d{1,2}/\\d{1,2}/\\d{4}\\s*",
            Pattern.CASE_INSENSITIVE);
    public static final String KEYWORD = "on";
    public static final String EXAMPLE_USAGE = "on 16/3/2021";
    public static final String ERROR_MESSAGE = "Command needs to be in form: on dd/mm/yyyy hh:mm";
    private String message;
    private LocalDate date;

    /**
     * @param taskList the <code>TaskList</code>
     * @param date the date to search for tasks on
     */
    public OnCommand(TaskList taskList, LocalDate date) {
        super(taskList);
        this.date = date;
        this.message = "These are your tasks on " + date + ":";
        this.isPersistentCommand = false;
    }

    /**
     * List all tasks that occur on specified date
     */
    public void execute() {
        String tasks = taskList.getTasksByFilter((Task t) -> t.getIsOn(date));
        Ui.printPretty(message + tasks);
    }
}
