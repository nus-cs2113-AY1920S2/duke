package duke.parser;

import duke.commands.*;
import duke.exceptions.CommandNotFoundException;
import duke.exceptions.EmptyDescriptionException;

import static duke.utils.Constants.LIST_COMMAND;
import static duke.utils.Constants.BYE_COMMAND;
import static duke.utils.Constants.DONE_COMMAND;
import static duke.utils.Constants.DELETE_COMMAND;
import static duke.utils.Constants.TODO_COMMAND;
import static duke.utils.Constants.DEADLINE_COMMAND;
import static duke.utils.Constants.EVENT_COMMAND;
import static duke.utils.Constants.CLEAR_COMMAND;


public class Parser {    
    
    public static Command parse(String input) throws CommandNotFoundException, EmptyDescriptionException{
        String[] split = input.split("\\s+", 2); // limit: the number of segments after split
        String command = split[0];
        
        try {
            switch (command) {
            case LIST_COMMAND:
                return new ListCommand();
            case DONE_COMMAND:
                return new DoneCommand(Integer.parseInt(split[1].trim()));
            case DELETE_COMMAND:
                return new DeleteCommand(Integer.parseInt(split[1].trim()));
            case TODO_COMMAND:
                return new TodoCommand(split[1]);
            case DEADLINE_COMMAND:
                return new DeadlineCommand(split[1]);
            case EVENT_COMMAND:
                return new EventCommand(split[1]);
            case CLEAR_COMMAND:
                return new ClearCommand();
            case BYE_COMMAND:
                return new ExitCommand();
            default:
                throw new CommandNotFoundException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(command);
        }
    }
}
