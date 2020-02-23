package command;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;
import static misc.Messages.MESSAGE_FIND_COMMAND_MISSING_KEYWORD;

import java.util.Optional;

import duke.Duke;

/**
 * Represents the 'Find' functionality of a command. A FindCommand contains
 * a keyword that will be pass onto Duke to find tasks that contains this
 * keyword.  
 * 
 */

public class FindCommand extends Command {
  
    private final String keyword;
    
    /**
     * Constructor of a FindCommand.
     * 
     * @param keyword A keyword that is used to find tasks that contains this word. 
     * @throws InvalidCommandException If the given keyword is a blank entry.
     */
    public FindCommand(Optional<String> keyword) {
        boolean hasKeyword = keyword.get().isBlank();

        if (!hasKeyword) {
            this.keyword = keyword.get();
        } else {
            throw new InvalidCommandException(MESSAGE_FIND_COMMAND_MISSING_KEYWORD);
        }
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeFindCommand(keyword);
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    }
}
