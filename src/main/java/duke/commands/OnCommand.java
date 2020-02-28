package duke.commands;

import duke.Main;
import duke.exceptions.BadLineFormatException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.time.LocalDate;

/**
 * Class for on command
 */
public class OnCommand extends Command {
    public static final String KEYWORD = "on";
    public static final String EXAMPLE_USAGE = "by 16/3/2021";
    private String message;

    private LocalDate date;

    public OnCommand(String keyword, String[] tokens, TaskList taskList) throws BadLineFormatException {
        super(keyword, tokens, taskList);
        if (tokens.length != 2) {
            throw new BadLineFormatException("Command needs to be in form: on d/M/yyyy");
        }
        this.date = LocalDate.parse(tokens[1], Main.DF);
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
