package duke.parser;

import duke.commands.ByCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.OnCommand;
import duke.commands.ToDoCommand;
import duke.exceptions.BadLineFormatException;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * A class containing static methods used to parse user input and lines that are read from file
 */
public class Parser {
    /**
     * Get a <code>Command</code> from a raw user inputted line
     * @param userInput raw user inputted line
     * @param taskList taskList is needed as a reference in <code>Command</code>
     * @return a <code>Command</code> based on the user input
     * @throws BadLineFormatException if the user input was badly formatted
     */
    public static Command parseUserInput(String userInput, TaskList taskList) throws BadLineFormatException {
        if (userInput.length() == 0) {
            throw new BadLineFormatException("Empty line");
        }

        final String WHITESPACE_REGEX = "\\s+";
        String[] tokens = userInput.split(WHITESPACE_REGEX);
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
        case ByCommand.KEYWORD:
            return new ByCommand(keyword, tokens, taskList);
        case OnCommand.KEYWORD:
            return new OnCommand(keyword, tokens, taskList);
        case DeadlineCommand.KEYWORD:
            return new DeadlineCommand(keyword, tokens, taskList);
        case DeleteCommand.KEYWORD:
            return new DeleteCommand(keyword, tokens, taskList);
        case DoneCommand.KEYWORD:
            return new DoneCommand(keyword, tokens, taskList);
        case EventCommand.KEYWORD:
            return new EventCommand(keyword, tokens, taskList);
        case FindCommand.KEYWORD:
            return new FindCommand(keyword, tokens, taskList);
        case ListCommand.KEYWORD:
            return new ListCommand(keyword, tokens, taskList);
        case ToDoCommand.KEYWORD:
            return new ToDoCommand(keyword, tokens, taskList);
        default:
            throw new BadLineFormatException("Unrecognized keyword");
        }
    }

    public static Task parseFormattedLine(String line) throws BadLineFormatException {
        String[] tokens = line.split(",");
        if (tokens.length == 0) {
            throw new BadLineFormatException("Empty line");
        } else if (tokens.length < 3) {
            throw new BadLineFormatException("Not enough tokens");
        } else if ((tokens[0].equals("D") || tokens[0].equals("E")) && tokens.length < 4) {
            throw new BadLineFormatException("Not enough tokens");
        } else if (!(tokens[1].equals("y") || tokens[1].equals("n"))) {
            throw new BadLineFormatException("Second token must be y or n");
        }

        boolean isDone = tokens[1].equals("y");
        switch(tokens[0]) {
        case "T":
            return new ToDo(tokens[2], isDone);
        case "D":
            return new Deadline(tokens[2], tokens[3], isDone);
        case "E":
            return new Event(tokens[2], tokens[3], isDone);
        default:
            throw new BadLineFormatException("First token must be T, D, or E");
        }
    }
}
