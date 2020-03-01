package duke;

import static duke.Duke.*;

/**
 * Parser class - A class to handle the input of users and pass it to
 * Command class for implementing
 */
public class Parser {
    public static final String SPACE = " ";

    protected TaskList tasks;

    /**
     * Empty Parser constructor to initialize an empty task list
     */
    public Parser() {
        this.tasks = new TaskList();
    }

    /**
     * a method to deal with user's input by extracting it and pass it
     * for the Command to process each indivdual's task
     *
     * @param fullCommand user's input
     * @return Command each user's input could provide different types
     * of tasks to process
     */
    public Command parse(String fullCommand) {
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
