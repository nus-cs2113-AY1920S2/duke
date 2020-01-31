package commands;

import commands.Command;
import commands.CommandResult;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Find all tasks matches the given description.\n"
            + "    Example: " + COMMAND_WORD + " read a book.";


    public FindCommand() {
        
    }

    @Override
    public CommandResult execute() {
        return null;
    }
}
