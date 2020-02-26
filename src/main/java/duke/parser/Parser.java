package duke.parser;

import duke.commands.*;
import duke.common.Messages;
import duke.data.exception.DukeException;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {

        String[] userCommand = fullCommand.split(" ", 2);

        if (userCommand[0].equalsIgnoreCase("bye")) {
            return new ExitCommand();

        } else if (userCommand[0].equalsIgnoreCase("list")) {
            return new ListCommand();

        } else if (userCommand[0].equalsIgnoreCase("done")) {
            if (userCommand.length == 1 || userCommand[1].trim().isEmpty()) {
                throw new DukeException(Messages.MESSAGE_EMPTY_INDEX);
            }
            int index = Integer.parseInt(userCommand[1]) - 1;
            return new DoneCommand(index);

        } else if (userCommand[0].equalsIgnoreCase("todo")) {
            if (userCommand.length == 1 || userCommand[1].trim().isEmpty()) {
                throw new DukeException(Messages.MESSAGE_EMPTY_DESCRIPTION);
            }
            return new AddCommand("todo", userCommand[1]);

        } else if (userCommand[0].equalsIgnoreCase("event")) {
            if (userCommand.length == 1 || userCommand[1].trim().isEmpty()) {
                throw new DukeException(Messages.MESSAGE_EMPTY_DESCRIPTION);
            }
            String[] buffer = userCommand[1].split("/at", 2);
            if (buffer.length == 1 || buffer[1].trim().isEmpty()) {
                throw new DukeException(Messages.MESSAGE_EMPTY_AT);
            }
            return new AddCommand("event", buffer[0].trim(), buffer[1].trim());

        } else if (userCommand[0].equalsIgnoreCase("deadline")) {
            if (userCommand.length == 1 || userCommand[1].trim().isEmpty()) {
                throw new DukeException(Messages.MESSAGE_EMPTY_DESCRIPTION);
            }

            String[] buffer = userCommand[1].split("/by", 2);
            if (buffer.length == 1 || buffer[1].trim().isEmpty()) {
                throw new DukeException(Messages.MESSAGE_EMPTY_BY);
            }
            return new AddCommand("deadline", buffer[0].trim(), buffer[1].trim());

        } else if (userCommand[0].equalsIgnoreCase("delete")) {
            if (userCommand.length == 1 || userCommand[1].trim().isEmpty()) {
                throw new DukeException(Messages.MESSAGE_EMPTY_INDEX);
            }
            int index = Integer.parseInt(userCommand[1]) - 1;
            return new DeleteCommand(index);

        } else if (userCommand[0].equalsIgnoreCase("find")) {
            if (userCommand.length == 1 || userCommand[1].trim().isEmpty()) {
                throw new DukeException(Messages.MESSAGE_EMPTY_DESCRIPTION);
            }
            return new FindCommand(userCommand[1]);
        } else {
            throw new DukeException(Messages.MESSAGE_INVALID_COMMAND);
        }
    }
}
