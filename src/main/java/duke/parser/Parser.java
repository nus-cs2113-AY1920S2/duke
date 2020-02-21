package duke.parser;

import duke.commands.*;
import duke.exception.FormatErrorException;
import duke.exception.IncompleteInputException;
import duke.exception.InvalidInputException;
import duke.exception.OutOfBoundsException;
import duke.task.TaskList;

public class Parser {

    public static Command parseCommand(String command, TaskList tasks) throws InvalidInputException,
            IncompleteInputException, FormatErrorException, OutOfBoundsException {
        Command newCommand;
        String[] phrases = command.split(" ");
        if (phrases.length == 0) {
            throw new InvalidInputException();
        }
        switch(phrases[0]) {
        case "bye":
            newCommand = new ByeCommand();
            break;
        case "list":
            newCommand = new ListCommand();
            break;
        case "done":
            if (phrases.length == 1) {
                throw new IncompleteInputException();
            }
            int index = Integer.parseInt(phrases[1]);
            if ((index > tasks.getSize()) || (index < 1)) {
                throw new OutOfBoundsException();
            }
            newCommand = new DoneCommand(index);
            break;
        case "deadline":
            if (phrases.length == 1) {
                throw new IncompleteInputException();
            }
            int position = command.indexOf("/by");
            if (position == -1) {
                throw new FormatErrorException();
            }
            String description = command.substring(phrases[0].length()+1,position-1);
            String by = command.substring(position+4);
            newCommand = new AddDeadlineCommand(description,by);
            break;
        case "event":
            if (phrases.length == 1) {
                throw new IncompleteInputException();
            }
            int pos = command.indexOf("/at");
            if (pos == -1) {
                throw new FormatErrorException();
            }
            description = command.substring(phrases[0].length()+1,pos-1);
            String duration = command.substring(pos+4);
            newCommand = new AddEventCommand(description,duration);
            break;
        case "todo":
            if (phrases.length == 1) {
                throw new IncompleteInputException();
            }
            description = command.substring(phrases[0].length()+1);
            newCommand = new AddTodoCommand(description);
            break;
        case "delete":
            if (phrases.length == 1) {
                throw new IncompleteInputException();
            }
            index = Integer.parseInt(phrases[1]);
            if ((index > tasks.getSize()) || (index < 1)) {
                throw new OutOfBoundsException();
            }
            newCommand = new DeleteCommand(index);
            break;
        case "clear":
            newCommand = new ClearCommand();
            break;
        case "help":
            newCommand = new HelpCommand();
            break;
        case "find":
            if (phrases.length == 1) {
                newCommand = new ListCommand();
                break;
            }
            newCommand = new FindCommand(phrases[1]);
            break;
        default:
            throw new InvalidInputException();
        }
        return newCommand;
    }
}
