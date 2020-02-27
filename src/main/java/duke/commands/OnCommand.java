package duke.commands;

import duke.Main;
import duke.exceptions.BadLineFormatException;
import duke.tasklist.TaskList;

import java.time.LocalDate;

public class OnCommand extends Command {
    public static final String KEYWORD = "on";
    public static final String EXAMPLE_USAGE = "by 16/3/2021";
    private LocalDate date;

    public OnCommand(String keyword, String[] tokens, TaskList taskList) throws BadLineFormatException {
        super(keyword, tokens, taskList);
        if (tokens.length != 2) {
            throw new BadLineFormatException("Command needs to be in form: on d/M/yyyy");
        }
        this.date = LocalDate.parse(tokens[1], Main.DF);
        this.isPersistentCommand = false;
    }

    public void execute() {
        taskList.listTasksOnDate(date);
    }
}
