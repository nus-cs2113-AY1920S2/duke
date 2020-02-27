package duke.parser;

import duke.commands.ByCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.OnCommand;
import duke.commands.ToDoCommand;
import duke.exceptions.BadLineFormatException;
import duke.tasklist.TaskList;

public class Parser {
    public static Command parseLine(String userInput, TaskList taskList) throws BadLineFormatException {
        if (userInput.length() == 0) {
            throw new BadLineFormatException("Empty line");
        }

        final String WHITESPACE_REGEX = "\\s+";
        String[] tokens = userInput.split(WHITESPACE_REGEX);
        String keyword = tokens[0];

        return getCommandFromKeyword(keyword, tokens, taskList);
    }

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
        case ListCommand.KEYWORD:
            return new ListCommand(keyword, tokens, taskList);
        case ToDoCommand.KEYWORD:
            return new ToDoCommand(keyword, tokens, taskList);
        default:
            throw new BadLineFormatException("Unrecognized keyword");
        }
    }
}
