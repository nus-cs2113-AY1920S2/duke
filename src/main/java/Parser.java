package src.main.java;

import src.main.java.duke.command.*;
import src.main.java.duke.exceptions.InvalidCommandException;
import src.main.java.duke.exceptions.InvalidDateException;

/** Return Commands based on user commands written in Command line.
 *
 * @param fullCommand  commands written by user
 * @param userCommand  command that are split up from @param fullCommand
 * @param taskDetails  description and date of task from @userCommand
 */
public class Parser {

    static Command parse(String fullCommand) throws InvalidCommandException, InvalidDateException {
        String[] userCommand= fullCommand.split(" ", 2);
        switch (userCommand[0]) {
            case AddTodoCommand.COMMAND_WORD:
                return new AddTodoCommand(userCommand[1]);

            case AddDeadlineCommand.COMMAND_WORD:
                String[] taskDetails = readTaskDetails(userCommand[1], " /by ");
                return new AddDeadlineCommand(taskDetails[0], taskDetails[1]);

            case AddEventCommand.COMMAND_WORD:
                taskDetails = readTaskDetails(userCommand[1], " /at ");
                return new AddEventCommand(taskDetails[0], taskDetails[1]);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case DoneCommand.COMMAND_WORD:
                return new DoneCommand(userCommand[1]);

            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(userCommand[1]);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
                
            case FindCommand.COMMAND_WORD:
                return new FindCommand(userCommand[1]);

            default:
                throw new InvalidCommandException();
        }
    }

    private static String[] readTaskDetails(String taskDescription, String delimiter) throws InvalidDateException {
        String[] taskDetails = taskDescription.split(delimiter, 2);
        if (taskDetails.length == 1) {
            throw new InvalidDateException();
        }
        return taskDetails;
    }
}
