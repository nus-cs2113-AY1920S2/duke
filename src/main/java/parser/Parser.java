package parser;

import commands.Command;
import commands.FindCommand;
import commands.EventCommand;
import commands.ToDoCommand;
import commands.DoneCommand;
import commands.DeleteCommand;
import commands.DeadlineCommand;
import commands.ListCommand;
import commands.ExitCommand;

import exceptions.UnknownCommandException;

/**
 * Parser that parses user input and returns the appropriate Command object that
 * corresponds to the command word inputted
 */
public class Parser {
    /**
     * Processes the user input from System.in and returns appropriate Command object
     * @param rawUserInput String provided by user
     * @return Command object that corresponds to the Command word
     * @throws UnknownCommandException throws when command word is unknown
     */
    public static Command parse(String rawUserInput)
            throws UnknownCommandException {
        String[] splitCommands = rawUserInput.trim().split(" ", 2);
        switch (splitCommands[0]) {
        case "list":
            return new ListCommand(rawUserInput);
        case "deadline":
            return new DeadlineCommand(rawUserInput);
        case "done":
            return new DoneCommand(rawUserInput);
        case "todo":
            return new ToDoCommand(rawUserInput);
        case "event":
            return new EventCommand(rawUserInput);
        case "delete":
            return new DeleteCommand(rawUserInput);
        case "find":
            return new FindCommand(rawUserInput);
        case "bye":
            return new ExitCommand(rawUserInput);
        default:
            throw new UnknownCommandException(rawUserInput);
        }
    }
}