package duke;

import duke.task.Task;

import java.util.ArrayList;

import static duke.Duke.*;

public class Parser {
    public static final String SPACE = " ";
    public static final String BYE_MESSAGE = "\tBye. Hope to see you again soon!";

    protected TaskList tasks;

    public Parser() {
        this.tasks = new TaskList();
    }

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public Command parse(String fullCommand) {
        //String[] commandSplitter = fullCommand.trim().split(SPACE);
        String commandReformatted = fullCommand.toLowerCase().trim();
        if (commandReformatted.equals("bye")) {
            return new ExitCommand();
        } else if (commandReformatted.equals("list")) {
            return new ShowCommand();
        } else {
            String[] commandSplitter = fullCommand.trim().split(SPACE);
            String prefix = commandSplitter[0].toLowerCase().trim();
            switch (prefix) {
            case DELETE:
                return new DeleteCommand(commandSplitter[1]);
            case DONE:
                return new MarkTaskCommand(commandSplitter[1]);
            case "find":
                return new FindCommand(commandSplitter[1]);
            case TODO:
            case DEADLINE:
            case EVENT:
            default:
                return new AddCommand(fullCommand,prefix);
            }
        }
    }


}
