package duke.parser;

import duke.commands.*;
import duke.exceptions.*;

public class Parser {

    private static String commandPrompt;
    private static String description;
    private static int numArguments;

    public static void prepareInput(String input) throws InvalidCommandException {
        String[] inputArray = input.trim().toLowerCase().split(" ", 2);
        numArguments = inputArray.length;
        if (numArguments == 0) {
            throw new InvalidCommandException();
        }
        if (numArguments == 2) {
            description = inputArray[1];
        }
        commandPrompt = inputArray[0];
    }

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
                command = new DeadlineCommand(commandPrompt, deadlineInfo[0], deadlineInfo[1]);
                break;
            case "event":
                if (numArguments != 2) throw new InvalidEventException();
                String[] eventInfo = description.split("/at ", 2);
                if (eventInfo.length != 2) throw new InvalidEventException();
                command = new EventCommand(commandPrompt, eventInfo[0], eventInfo[1]);
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
