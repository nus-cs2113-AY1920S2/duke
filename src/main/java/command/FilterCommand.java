package command;

import duke.Duke;

import static misc.Messages.MESSAGE_COMMAND_RESULT_SUCCESS;

public class FilterCommand extends Command {
    
    private final String date;
  
    public FilterCommand(String date) {
        this.date = date;
    }
    
    @Override
    public CommandResult execute(Duke duke) {
        duke.executeFilterCommand(date);
        return new CommandResult(MESSAGE_COMMAND_RESULT_SUCCESS);
    }
}