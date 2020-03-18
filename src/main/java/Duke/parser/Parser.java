package duke.parser;

import duke.commands.*;
import duke.exceptions.MissingDescriptionException;

import static duke.constants.Constants.DESCRIPTIONS_MISSING;
import static duke.constants.Constants.TASK_NUM_DELETED;
import static duke.constants.Constants.TASK_NUM_COMPLETED;
import static duke.constants.Constants.FIND_DESCRIPTIONS;

import java.util.Scanner;

public class Parser {

    /**
     * A parser method to take in user input and return a command object
     *
     * @param myScanner Scanner for reading in user input
     * @param function  String containing the different command function
     * @return a command object to main
     */
    public Command parse(Scanner myScanner, String function) {
        Command command = null;
        try {
            String restOfInput = myScanner.nextLine().trim();
            switch (function) {
                case "todo":
                    if (restOfInput.isEmpty()) {
                        throw new MissingDescriptionException(DESCRIPTIONS_MISSING);
                    }
                    command = new TodoCommand(restOfInput);
                    break;

                case "deadline":
                    if (restOfInput.isEmpty()) {
                        throw new MissingDescriptionException(DESCRIPTIONS_MISSING);
                    }
                    command = new DeadlineCommand(restOfInput);
                    break;

                case "event":
                    if (restOfInput.isEmpty()) {
                        throw new MissingDescriptionException(DESCRIPTIONS_MISSING);
                    }
                    command = new EventCommand(restOfInput);
                    break;

                case "bye":
                    command = new ExitCommand();
                    break;

                case "list":
                    command = new ListCommand();
                    break;

                case "done":
                    if (restOfInput.isEmpty()) {
                        throw new MissingDescriptionException(TASK_NUM_COMPLETED);
                    }
                    command = new DoneCommand(restOfInput);
                    break;

                case "delete":
                    if (restOfInput.isEmpty()) {
                        throw new MissingDescriptionException(TASK_NUM_DELETED);
                    }
                    command = new DeleteCommand(restOfInput);
                    break;

                case "reset":
                    command = new ResetCommand();
                    break;

                case "find":
                    if (restOfInput.isEmpty()) {
                        throw new MissingDescriptionException(FIND_DESCRIPTIONS);
                    }
                    command = new FindCommand(restOfInput);
                    break;

                case "help":
                    command = new HelpCommand();
                    break;

                default:
                    command = new InputErrorCommand();
                    break;
            }
        } catch (MissingDescriptionException e) {
            e.printDescr();
        }

        return command;
    }
}
