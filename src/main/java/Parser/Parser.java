package Parser;

import Commands.*;

import Exceptions.MissingDescriptionException;
import Exceptions.UnknownCommandException;

public class Parser {
    public Parser() {
    }

    public static Command parse(String rawUserInput)
            throws UnknownCommandException, MissingDescriptionException {
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
        case "bye":
            return new ExitCommand(rawUserInput);
        default:
            throw new UnknownCommandException(rawUserInput);
        }
    }
}
