package duke;

import duke.commands.*;
import duke.exceptions.InvalidCommandException;

public class Parser {

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
        } else if(newData[0].toLowerCase().equals("delete")){
            return new DeleteCommand(userData);
        } else if(newData[0].toLowerCase().equals("bye")) {
            return new ExitCommand();
        } else {
            throw new InvalidCommandException("Invalid command.");
        }
    }
}
