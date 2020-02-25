package duke.commands;
/**
 * Shows the list of tasks to the user
 */

import static duke.Duke.tasks;

public class ListCommand extends Command {
    
    public static final String COMMAND_WORD = "list";
    
    private static final String COMMAND_LIST_DESC = "Displays all the tasks in the list with index numbers.";
    private static final String COMMAND_LIST_EXAMPLE = COMMAND_WORD;
    
    public static final String MESSAGE_USAGE =
            String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD, COMMAND_LIST_DESC) + LS +
                    String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_LIST_EXAMPLE) + LS;
    
    private static final String COMMAND_LIST_EMPTY_MESSAGE = TAB + "The list is empty!";
    private static final String COMMAND_LIST_MESSAGE = "Here are the tasks in your list:";
    
    /**
     * Execute the list operation flow
     *
     * @return the list of tasks to the user
     */
    @Override
    public CommandResult execute() {
        if (tasks.isEmpty()) {
            return new CommandResult(DIVIDER + LS + COMMAND_LIST_EMPTY_MESSAGE + LS + DIVIDER);
        }
        StringBuilder output = new StringBuilder();
        output.append(LS).append(COMMAND_LIST_MESSAGE).append(LS);
        for (int i = 0; i < tasks.size(); ++i) {
            output.append(i + 1).append(".").append(tasks.get(i)).append(LS);
        }
        return new CommandResult(DIVIDER + output + DIVIDER);
    }
    
}


