package duke.commands;

/**
 * Interface to facilitate string format for commands
 */
public interface HelpFormat {
    
    String DIVIDER = "____________________________________________________________";
    String LS = System.lineSeparator();
    String TAB = "\t";
    String MESSAGE_COMMAND_HELP = "%s: %s";
    String MESSAGE_COMMAND_HELP_EXAMPLE = TAB + "Example: %s";
    String MESSAGE_COMMAND_HELP_PARAMETER = TAB + "Parameter(s): %s";
    
}

