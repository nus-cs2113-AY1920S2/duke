package duke.parser;

import duke.commands.*;
import duke.exceptions.*;

/**
 * Parser is the public class responsible for parsing user input and generating the relevant commands.
 */

public class Parser {

    /**
     * The command prompt entered by the user.
     */

    private static String commandPrompt;

    /**
     * The description of the command entered by the user.
     */

    private static String description;

    /**
     * The number of relevant arguments in the command entered by the user.
     */

    private static int numArguments;

    /**
     * Parses the user input and prepares it to be analysed and used to generate commands.
     * @param input the user input.
     * @throws InvalidCommandException if user input has too few arguments.
     */

    public static void prepareInput(String input) throws InvalidCommandException {
        String[] inputArray = input.trim().toLowerCase().split(" ", 2);
        numArguments = inputArray.length;
        if (numArguments == 0) {
            throw new InvalidCommandException();
        }
        if (numArguments == 2) {
            description = inputArray[1].trim();
        }
        commandPrompt = inputArray[0].trim();
    }

    /**
     * Analyses the user input and generates the relevant command.
     * @param input the user input.
     * @return  the command generated from the user input.
     * @throws InvalidCommandException if command is not supported by application.
     * @throws InvalidDeadlineException if format for creating new deadline is wrong.
     * @throws InvalidEventException if format for creating new event is wrong.
     * @throws InvalidToDoException if format for creating new to-do is wrong.
     * @throws InvalidFormatException if format for command is wrong.
     */

    public static Command parseInput(String input) throws InvalidCommandException, InvalidDeadlineException, InvalidEventException, InvalidToDoException, InvalidFormatException {
        prepareInput(input);
        Command command;
        switch (commandPrompt) {
            case "list":
                command = new ListCommand(commandPrompt);
                break;
            case "todo":
                if (numArguments != 2) throw new InvalidToDoException();
                command = new ToDoCommand(commandPrompt, description);
                break;
            case "deadline":
                if (numArguments != 2) throw new InvalidDeadlineException();
                String[] deadlineInfo = description.split("/by ", 2);
                if (deadlineInfo.length != 2) throw new InvalidDeadlineException();
                command = new DeadlineCommand(commandPrompt, deadlineInfo[0].trim(), deadlineInfo[1].trim());
                break;
            case "event":
                if (numArguments != 2) throw new InvalidEventException();
                String[] eventInfo = description.split("/at ", 2);
                if (eventInfo.length != 2) throw new InvalidEventException();
                command = new EventCommand(commandPrompt, eventInfo[0].trim(), eventInfo[1].trim());
                break;
            case "done":
                if (!description.matches("-?\\d+") || numArguments != 2) throw new InvalidFormatException();
                command = new DoneCommand(commandPrompt, description);
                break;
            case "delete":
                if (!description.matches("-?\\d+") || numArguments != 2) throw new InvalidFormatException();
                command = new DeleteCommand(commandPrompt, description);
                break;
            case "find":
                if (numArguments != 2) throw new InvalidFormatException();
                command = new FindCommand(commandPrompt, description);
                break;
            case "clear":
                command = new ClearCommand(commandPrompt);
                break;
            case "/help":
                command = new HelpCommand(commandPrompt);
                break;
            case "bye":
                command = new ByeCommand(commandPrompt);
                break;
            default:
                throw new InvalidCommandException();
        }
        return command;
    }

}
