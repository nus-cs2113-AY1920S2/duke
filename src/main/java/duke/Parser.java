package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;

public class Parser {

    public static Command parseCommand(String fullCommand) {
        String[] commandTokens = fullCommand.split(" ");
        String commandType = commandTokens[0].toLowerCase();

        switch (commandType) {
        case ByeCommand.BYE_COMMAND_NAME:
            // exit duke
            return new ByeCommand();
        case ListCommand.LIST_COMMAND_NAME:
            // show all tasks in the list
            return new ListCommand();
        case DeleteCommand.DELETE_COMMAND_NAME:
            // delete a task by its displayed list index
            return prepareDeleteCommand(commandTokens);
        case DoneCommand.DONE_COMMAND_NAME:
            // mark a task as done
            return prepareDoneCommand(commandTokens);
        case TodoCommand.TODO_COMMAND_NAME:
            // add to`do to tasks
            return prepareTodoCommand(fullCommand);
        case DeadlineCommand.DEADLINE_COMMAND_NAME:
            // add deadline to tasks
            return prepareDeadlineCommand(fullCommand);
        case EventCommand.EVENT_COMMAND_NAME:
            // add event to tasks
            return prepareEventCommand(fullCommand);
        default:
            return new IncorrectCommand(Ui.UNKNOWN_COMMAND_NAME_MESSAGE);
        }
    }

    private static Command prepareDeadlineCommand(String fullCommand) {
        // deadline command follows format <taskType> <taskName> /<date>
        String[] deadlineInfo = null;
        try {
            deadlineInfo = fullCommand
                    .substring(DeadlineCommand.DEADLINE_COMMAND_NAME.length() + 1)
                    .trim()
                    .split(DeadlineCommand.COMMAND_DATE_TIME_DELIMITER, 2);
        } catch (StringIndexOutOfBoundsException e) {
            return new IncorrectCommand(Ui.DEADLINE_INSUFFICIENT_ARGS_MESSAGE);
        }

        if (deadlineInfo.length != 2) {
            return new IncorrectCommand(Ui.DEADLINE_INSUFFICIENT_ARGS_MESSAGE);
        }
        String deadlineName = deadlineInfo[0].trim();
        String deadlineDate = deadlineInfo[1].trim();
        return new DeadlineCommand(deadlineName, deadlineDate);
    }

    private static Command prepareDeleteCommand(String[] commandTokens) {
        try {
            int deleteIndex = Integer.parseInt(commandTokens[1]) - 1;
            return new DeleteCommand(deleteIndex);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            // deleteIndex is not specified
            return new IncorrectCommand(Ui.TASK_NUMBER_NOT_SPECIFIED_MESSAGE);
        } catch (NumberFormatException nfe) {
            // commandTokens[1] is unable to be parsed as an int
            return new IncorrectCommand(Ui.TASK_NUMBER_NOT_INTEGER_MESSAGE);
        }
    }

    private static Command prepareDoneCommand(String[] commandTokens) {
        try {
            int doneIndex = Integer.parseInt(commandTokens[1]) - 1;
            return new DoneCommand(doneIndex);
        } catch (IndexOutOfBoundsException aioobe) {
            // taskIndex is not specified
            return new IncorrectCommand(Ui.TASK_NUMBER_NOT_SPECIFIED_MESSAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(Ui.TASK_NUMBER_NOT_INTEGER_MESSAGE);
        }
    }

    private static Command prepareEventCommand(String fullCommand) {
        // event command follows format <taskType> <taskName> /<date>
        String[] eventInfo = null;
        try {
            eventInfo = fullCommand
                    .substring(EventCommand.EVENT_COMMAND_NAME.length() + 1)
                    .trim()
                    .split(EventCommand.COMMAND_DATE_TIME_DELIMITER, 2);
        } catch (StringIndexOutOfBoundsException e) {
            return new IncorrectCommand(Ui.EVENT_INSUFFICIENT_ARGS_MESSAGE);
        }

        if (eventInfo.length != 2) {
            return new IncorrectCommand(Ui.EVENT_INSUFFICIENT_ARGS_MESSAGE);
        }
        String eventName = eventInfo[0].trim();
        String eventDate = eventInfo[1].trim();
        return new EventCommand(eventName, eventDate);
    }

    private static Command prepareTodoCommand(String fullCommand) {
        // to`do command follows format <taskType> <taskName>
        try {
            String description = fullCommand.substring(TodoCommand.TODO_COMMAND_NAME.length() + 1).trim();
            return new TodoCommand(description);
        } catch (StringIndexOutOfBoundsException e) {
            return new IncorrectCommand(Ui.TODO_INSUFFICIENT_ARGS_MESSAGE);
        }
    }
}
