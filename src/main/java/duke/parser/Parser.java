package duke.parser;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.ByeCommand;
import duke.commands.ClearCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.exception.FormatErrorException;
import duke.exception.IncompleteInputException;
import duke.exception.InvalidInputException;
import duke.exception.OutOfBoundsException;
import duke.task.TaskList;

public class Parser {

    private static Command newCommand;

    private static String[] phrases;


    private static String commandWord;

    private static int index;

    public static Command parseCommand(String command, TaskList tasks) throws InvalidInputException,
            IncompleteInputException, FormatErrorException, OutOfBoundsException {
        prepareCommand(command);
        switch(commandWord) {
        case "bye":
            createByeCommand();
            break;
        case "list":
            createListCommand();
            break;
        case "done":
            checkCompleteInput();
            checkWithinBounds(tasks);
            createDoneCommand();
            break;
        case "deadline":
            checkCompleteInput();
            createDeadlineCommand(command);
            break;
        case "event":
            checkCompleteInput();
            createEventCommand(command);
            break;
        case "todo":
            checkCompleteInput();
            createTodoCommand(command);
            break;
        case "delete":
            checkCompleteInput();
            checkWithinBounds(tasks);
            createDeleteCommand();
            break;
        case "clear":
            createClearCommand();
            break;
        case "help":
            createHelpCommand();
            break;
        default:
            throw new InvalidInputException();
        }
        return newCommand;
    }

    private static void createHelpCommand() {
        newCommand = new HelpCommand();
    }

    private static void createClearCommand() {
        newCommand = new ClearCommand();
    }

    private static void createDeleteCommand() {
        newCommand = new DeleteCommand(index);
    }

    private static void createTodoCommand(String command) {
        String description = command.substring(phrases[0].length()+1);
        newCommand = new AddTodoCommand(description);
    }

    private static void createEventCommand(String command) throws FormatErrorException {
        int pos = command.indexOf("/at");
        if (pos == -1) {
            throw new FormatErrorException();
        }
        String description = command.substring(phrases[0].length()+1,pos-1);
        String duration = command.substring(pos+4);
        newCommand = new AddEventCommand(description,duration);
    }

    private static void createDeadlineCommand(String command) throws FormatErrorException {
        int position = command.indexOf("/by");
        if (position == -1) {
            throw new FormatErrorException();
        }
        String description = command.substring(phrases[0].length()+1,position-1);
        String by = command.substring(position+4);
        newCommand = new AddDeadlineCommand(description,by);
    }

    private static void createDoneCommand() {
        newCommand = new DoneCommand(index);
    }

    private static void checkWithinBounds(TaskList tasks) throws OutOfBoundsException {
        index = Integer.parseInt(phrases[1]);
        if ((index > tasks.getSize()) || (index < 1)) {
            throw new OutOfBoundsException();
        }
    }

    private static void checkCompleteInput() throws IncompleteInputException {
        if (phrases.length == 1) {
            throw new IncompleteInputException();
        }
    }

    private static void createListCommand() {
        newCommand = new ListCommand();
    }

    private static void createByeCommand() {
        newCommand = new ByeCommand();
    }


    public static void prepareCommand(String command) throws InvalidInputException {
        phrases = command.split(" ");
        if (phrases.length == 0) {
            throw new InvalidInputException();
        } else {
            commandWord = phrases[0];
        }
    }
}
