package duke.parser;

import duke.commands.*;
import duke.exceptions.*;

public class Parser {

    private static String commandPrompt;
    private static String description;
    private static int numArguments;

    public static void prepareInput(String input) throws InvalidCommandException {
        String[] inputArray = input.toLowerCase().trim().split(" ", 2);
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
                command = new todoCommand(commandPrompt, description);
                break;
            case "deadline":
                if (numArguments != 2) throw new InvalidDeadlineException();
                String[] deadlineInfo = description.split("/by ", 2);
                if (deadlineInfo.length != 2) throw new InvalidDeadlineException();
                command = new deadlineCommand(commandPrompt, deadlineInfo[0], deadlineInfo[1]);
                break;
            case "event":
                if (numArguments != 2) throw new InvalidEventException();
                String[] eventInfo = description.split("/at ", 2);
                if (eventInfo.length != 2) throw new InvalidEventException();
                command = new eventCommand(commandPrompt, eventInfo[0], eventInfo[1]);
                break;
            case "done":
                if (!description.matches("-?\\d+") || numArguments != 2) throw new InvalidFormatException();
                command = new doneCommand(commandPrompt, description);
                break;
            case "delete":
                if (!description.matches("-?\\d+") || numArguments != 2) throw new InvalidFormatException();
                command = new deleteCommand(commandPrompt, description);
                break;
            case "find":
                if (numArguments != 2) throw new InvalidFormatException();
                command = new findCommand(commandPrompt, description);
                break;
            case "bye":
                command = new byeCommand(commandPrompt);
                break;
            default:
                throw new InvalidCommandException();
        }
        return command;
    }

}
