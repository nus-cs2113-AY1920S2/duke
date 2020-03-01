package duke.command;

/**
 * A class representing a command to display help information.
 */
public class HelpCommand extends Command {


    public HelpCommand () {
        super(null);
    }


    public CommandResult execute() {

        String msg = "Below are descriptions of the supported commands:" + System.lineSeparator();

        // help for exit command
        msg += System.lineSeparator() + String.format("\t%s: exits the program. " +
                "Input must follow the format below,", CMD_EXIT);
        msg += System.lineSeparator() + "\t\t" + ExitCommand.USAGE;
        msg += System.lineSeparator();

        // help for done command
        msg += System.lineSeparator() + String.format("\t%s: marks task as done. " +
                "Input must follow the format below,", CMD_DONE);
        msg += System.lineSeparator() + "\t\t" + DoneCommand.USAGE;
        msg += System.lineSeparator();

        // help for done command
        msg += System.lineSeparator() + String.format("\t%s: lists the tasks currently available. " +
                "Input must follow the format below,", CMD_LIST);
        msg += System.lineSeparator() + "\t\t" + ListCommand.USAGE;
        msg += System.lineSeparator();

        // help for deadline command
        msg += System.lineSeparator() + String.format("\t%s: adds deadline to your list of tasks. " +
                "Input must follow the format below,", CMD_ADD_DEADLINE);
        msg += System.lineSeparator() + "\t\t" + DeadlineCommand.USAGE;
        msg += System.lineSeparator();

        // help for event command
        msg += System.lineSeparator() + String.format("\t%s: adds event to your list of tasks. Input " +
                "must follow the format below,", CMD_ADD_EVENT);
        msg += System.lineSeparator() + "\t\t" + EventCommand.USAGE;
        msg += System.lineSeparator();

        // help for to do command
        msg += System.lineSeparator() + String.format("\t%s: add todo to your list of tasks. Input" +
                " must follow the format below,", CMD_ADD_TODO);
        msg += System.lineSeparator() + "\t\t" + TodoCommand.USAGE;
        msg += System.lineSeparator();

        // help for delete command
        msg += System.lineSeparator() + String.format("\t%s: removes specified task from the list. Input" +
                " must follow the format below,", CMD_DELETE);
        msg += System.lineSeparator() + "\t\t" + DeleteCommand.USAGE;
        msg += System.lineSeparator();

        // help for find command
        msg += System.lineSeparator() + String.format("\t%s: finds the tasks that contain the given pattern. Input" +
                " must follow the format below,", CMD_FIND);
        msg += System.lineSeparator() + "\t\t" + FindCommand.USAGE;
        msg += System.lineSeparator();

        // help for clear window
        msg += System.lineSeparator() + String.format("\t%s: clears command window. Input" +
                " must follow the format below,", CMD_CLEAR_WINDOW);
        msg += System.lineSeparator() + "\t\t" + ClearCommand.USAGE;
        msg += System.lineSeparator();

        return new CommandResult(msg);
    }

}
