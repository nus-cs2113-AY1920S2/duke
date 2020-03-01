package duke.parser;
import duke.command.Command;
import duke.command.CommandToDo;
import duke.command.CommandEvent;
import duke.command.CommandDeadline;
import duke.command.CommandDone;
import duke.command.CommandDelete;
import duke.command.CommandList;
import duke.command.CommandClear;
import duke.command.CommandBye;
import duke.command.FalseCommand;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<commandWord>^[\\S]+)(?<arguments>[\\d\\s\\S]*$)");
    public static final String INVALID_TASK = "Sheena: Erm. This number is invalid..Maybe try another number?";
    public static final String ERROR = "Sheena: Erm, The %s of a %s command cannot be empty.";
    public static final String TRY_AGAIN = "Sheena: Try again maybe? This is not part of the command.";

    public Command parseCommand(String userInput) {
        final Matcher matcher = COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            System.out.println("Sheena: Erm, This is a wrong command. Wanna try again? ");
        }

        final String commandWord = matcher.group("commandWord").trim();
        final String arguments = matcher.group("arguments").trim();

        if (commandWord.equals("bye")) {

            return new CommandBye();

        } else if (commandWord.equals("list")) {

            return new CommandList();

        } else if (commandWord.equals("done")) {

            try {
                final int targetIndex = parseArgsAsDisplayedIndex(arguments);
                return new CommandDone(targetIndex);
            } catch (NumberFormatException nfe) {
                return new FalseCommand(INVALID_TASK);
            }

        } else if (commandWord.equals("todo")) {
            try {
                return new CommandToDo(arguments);
            } catch (NullPointerException npe) {
                return new FalseCommand(String.format(ERROR, "description", "todo"));
            }

        } else if (commandWord.equals("event")) {

            try {
                final int indexOfAtPrefix = arguments.indexOf("/at");

                String description = arguments.substring(0, indexOfAtPrefix);
                String timeOfEvent = arguments.substring(indexOfAtPrefix + 3).trim();
                return new CommandEvent(description, timeOfEvent);
            } catch (StringIndexOutOfBoundsException iob) {
                return new FalseCommand(String.format(ERROR, "description and/or time", "event"));
            }

        } else if (commandWord.equals("deadline")) {

            try {
                final int indexOfByPrefix = arguments.trim().indexOf("/by");
                String description = arguments.trim().substring(0, indexOfByPrefix);
                String dueDate = arguments.substring(indexOfByPrefix + 3).trim();
                return new CommandDeadline(description, dueDate);
            } catch (StringIndexOutOfBoundsException iob) {
                return new FalseCommand(String.format(ERROR, "description and/or due date", "deadline"));
            }

        } else if (commandWord.equals("delete")) {

            try {
                final int targetIndex = parseArgsAsDisplayedIndex(arguments);
                return new CommandDelete(targetIndex);
            } catch (NumberFormatException nfe) {
                return new FalseCommand(INVALID_TASK);
            }

        } else if (commandWord.equals("clear")){

            return new CommandClear();

        } else {
            return new FalseCommand(TRY_AGAIN);
        }

    }

    private int parseArgsAsDisplayedIndex(String args) throws NumberFormatException {
        return Integer.parseInt(args.trim());
    }
}

