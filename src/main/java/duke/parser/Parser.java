package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     * @param fullCommand full user input command.
     * @return the command based on user input.
     * @throws DukeException if user input command is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String taskType;
        String args;
        String[] words;
        words = fullCommand.split("\\s+",2);
        taskType = words[0];

        String taskTypeLowerCase = getTaskTypeLowerCase(taskType);

        boolean isNotEmpty = words.length > 1;
        if(isNotEmpty){
            args = fullCommand.replace(taskType + " ", "");
        } else {
            args = "";
        }

        switch (taskTypeLowerCase) {
        case AddCommand.COMMAND_WORD :
            return new AddCommand(fullCommand, taskType, args);
        case ListCommand.COMMAND_WORD :
            return new ListCommand(fullCommand, taskType, args);
        case DeleteCommand.COMMAND_WORD :
            return new DeleteCommand(fullCommand, taskType, args);
        case DoneCommand.COMMAND_WORD :
            return new DoneCommand(fullCommand, taskType, args);
        case ExitCommand.COMMAND_WORD :
            return new ExitCommand(fullCommand, taskType, args);
        case FindCommand.COMMAND_WORD :
            return new FindCommand(fullCommand, taskType, args);
        default:
            throw new DukeException("Your command cannot be used.");
        }
    }

    /**
     * Translates user input into task type string in lower case.
     * @param taskType the user input task type.
     * @return task type string in lower case.
     */
    private static String getTaskTypeLowerCase(String taskType) {
        String taskTypeLowerCase = taskType.toLowerCase();

        boolean isAdd = taskTypeLowerCase.equals(AddCommand.TODO_COMMAND_WORD)
                || taskTypeLowerCase.equals(AddCommand.DEADLINE_COMMAND_WORD)
                || taskTypeLowerCase.equals(AddCommand.EVENT_COMMAND_WORD);
        if (isAdd){
            taskTypeLowerCase = AddCommand.COMMAND_WORD;
        }
        return taskTypeLowerCase;
    }


}
