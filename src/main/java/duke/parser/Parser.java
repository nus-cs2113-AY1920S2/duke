package duke.parser;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;
import duke.exceptions.BadLineFormatException;
import duke.tasklist.TaskList;

/**
 * A class containing static methods used to parse user input and lines that are read from file
 */
public class Parser {
    private static final String whiteSpaceRegex = "\\s+";

    /**
     * Get a <code>Command</code> from a raw user inputted line
     * @param userInput raw user inputted line
     * @param taskList taskList is needed as a reference in <code>Command</code>
     * @return a <code>Command</code> based on the user input
     * @throws BadLineFormatException if the user input was badly formatted
     */
    public static Command parseLine(String userInput, TaskList taskList) throws BadLineFormatException {
        if (userInput.length() == 0) {
            throw new BadLineFormatException("Empty line");
        }
        String[] tokens = userInput.split(whiteSpaceRegex);
        String keyword = tokens[0];

        return getCommandFromKeyword(keyword, tokens, taskList);
    }

    /**
     * Get the correct <code>Command</code> based on keyword
     * @param keyword first token in user input
     * @param tokens tokens from user input split by whitespace
     * @param taskList taskList is needed as a reference in <code>Command</code>
     * @return a <code>Command</code> based on keyword and tokens
     * @throws BadLineFormatException if the user input is badly formatted and <code>Command</code> can't be constructed
     */
    private static Command getCommandFromKeyword(String keyword, String[] tokens, TaskList taskList)
            throws BadLineFormatException {
        switch(keyword.toLowerCase()) {
        case DeadlineCommand.KEYWORD:
            return new DeadlineCommand(keyword, tokens, taskList);
        case DeleteCommand.KEYWORD:
            return new DeleteCommand(keyword, tokens, taskList);
        case DoneCommand.KEYWORD:
            return new DoneCommand(keyword, tokens, taskList);
        case EventCommand.KEYWORD:
            return new EventCommand(keyword, tokens, taskList);
        case ListCommand.KEYWORD:
            return new ListCommand(keyword, tokens, taskList);
        case ToDoCommand.KEYWORD:
            return new ToDoCommand(keyword, tokens, taskList);
        default:
            throw new BadLineFormatException("Unrecognized keyword");
        }
    }
}
