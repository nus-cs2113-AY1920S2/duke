package duke.parser;

import duke.task.TaskList;
import duke.command.*;

import static duke.constant.Constant.*;

/**
 * Parser class - A class to handle the input of users and pass it to
 * Command class for implementing
 */
public class Parser {
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
        //make the command to lower case without leading spaces
        String commandReformatted = fullCommand.toLowerCase().trim();

        if (commandReformatted.equals(BYE)) {
            return new ExitCommand();
        } else if (commandReformatted.equals(LIST)) {
            return new ShowCommand();
        } else {
            // split the string into array of words
            String[] commandSplitter = fullCommand.trim().split(SPACE);

            // get the prefix, which is the first word in the command and
            // pass them for Command sub-classes to process
            String prefix = commandSplitter[0].toLowerCase().trim();
            switch (prefix) {
            case DELETE:
                return new DeleteCommand(commandSplitter[1]);
            case DONE:
                return new MarkTaskCommand(commandSplitter[1]);
            case FIND:
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
