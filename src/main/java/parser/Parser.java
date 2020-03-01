package parser;

import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.AddDeadlineCommand;
import commands.DeleteCommand;
import commands.Command;
import commands.DoneCommand;
import commands.ListCommand;
import commands.ExitCommand;
import commands.FindCommand;

import exception.DukeException;

public class Parser {

    public static Command parse(String command) throws DukeException {
        String[] arguments = command.split(" ",2);

        switch (arguments[0]){

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case AddTodoCommand.COMMAND_WORD:
            return prepareAddTodo(arguments);

        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadline(arguments);

        case AddEventCommand.COMMAND_WORD:
            return prepareAddEvent(arguments);

        case ListCommand.COMMAND_WORD:
            return prepareListTasks(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteTask(arguments);

        case DoneCommand.COMMAND_WORD:
            return prepareDoneTask(arguments);

        case FindCommand.COMMAND_WORD:
            return prepareFindTask(arguments);

        default:
            throw new DukeException("Invalid Command.");
        }
    }

    private static Command prepareAddTodo (String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        if (arguments[1] == null || arguments[1].isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        return new AddTodoCommand(arguments[1]);
    }

    private static Command prepareAddDeadline (String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("Missing description or deadline.");
        }

        String[] details = arguments[1].split("/by ");

        if (details[0] == null || details[0].isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        if (details.length != 2) {
            throw new DukeException("Incorrect format.");
        }

        return new AddDeadlineCommand(details[0], details[1]);
    }

    private static Command prepareAddEvent (String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("Missing description or date and time.");
        }

        String[] details = arguments[1].split("/at ");

        if (details[0] == null || details[0].isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        if (details.length != 2) {
            throw new DukeException("Incorrect format.");
        }

        return new AddEventCommand(details[0], details[1]);
    }

    private static Command prepareListTasks (String[] arguments) throws DukeException {
        if (arguments.length > 1) {
            throw new DukeException("Incorrect format.");
        }

        return new ListCommand();
    }

    private static Command prepareDeleteTask (String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("The task item has to be indicated.");
        }

        try {
            int itemNumber= Integer.parseInt(arguments[1]);
            return new DeleteCommand(itemNumber-1);
        } catch (NumberFormatException  e) {
            throw new DukeException("The task item has to be an integer.");
        }
    }

    private static Command prepareDoneTask (String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("The task item has to be indicated.");
        }

        try {
            int itemNumber= Integer.parseInt(arguments[1]);
            return new DoneCommand(itemNumber-1);
        } catch (NumberFormatException  e) {
            throw new DukeException("The task item has to be an integer.");
        }
    }

    private static Command prepareFindTask (String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("The keyword has to be indicated.");
        }

        return new FindCommand(arguments[1]);
    }
}
