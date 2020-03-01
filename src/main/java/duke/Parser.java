package duke;

import duke.commands.*;
import duke.exceptions.InvalidCommandException;

/**
 * Parser class to parse user inputs and identify user commands.
 */
public class Parser {

    /**
     * Processes user inputs and execute respective commands.
     * @param userData String of user input.
     * @return ExecuteCommand object given by user.
     * @throws InvalidCommandException If command is invalid.
     */
    public static ExecuteCommand parse (String userData) throws InvalidCommandException {
        String[] newData = userData.split(" ");

        if (newData[0].toLowerCase().equals("list")) {
            return new ListCommand();
        } else if (newData[0].toLowerCase().equals("done")) {
            return new DoneCommand(userData);
        } else if (newData[0].toLowerCase().equals("deadline")) {
            return new DeadlineCommand(userData);
        } else if (newData[0].toLowerCase().equals("event")) {
            return new EventCommand(userData);
        } else if (newData[0].toLowerCase().equals("todo")) {
            return new TodoCommand(userData);
        } else if (newData[0].toLowerCase().equals("find")) {
            return new FindCommand(userData);
        } else if(newData[0].toLowerCase().equals("delete")){
            return new DeleteCommand(userData);
        } else if(newData[0].toLowerCase().equals("bye")) {
            return new ExitCommand();
        } else {
            throw new InvalidCommandException("Invalid command.");
        }
    }
}
