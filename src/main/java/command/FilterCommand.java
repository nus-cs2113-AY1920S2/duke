package command;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;
import static misc.Messages.MESSAGE_FILTER_COMMAND_MISSING_DATE;

import java.util.Optional;

import duke.Duke;

/**
 * Represents the 'Filter' functionality of a command. A FilterCommand contains
 * a date that will be pass onto Duke to filter tasks based on the given date.  
 * 
 */
public class FilterCommand extends Command {
    
    /** A String representation of a date that serves as a predicate. */
    private final String date;
  
    /**
     * Constructor for a FilterCommand.
     * 
     * @param date A date to be used as a predicate for filtering tasks.
     * @throws InvalidCommandException If the given date is a blank entry.
     */
    public FilterCommand(Optional<String> date) {
        boolean hasDate = date.get().isBlank();

        if (!hasDate) {
            this.date = date.get();
        } else {
            throw new InvalidCommandException(
                    MESSAGE_FILTER_COMMAND_MISSING_DATE);
        }
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeFilterCommand(date);
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    }
}