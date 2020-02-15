package command;

import duke.Duke;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;

public class FindCommand extends Command {
  
    private final String keyword;
    
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeFindCommand(keyword);
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    }
}
