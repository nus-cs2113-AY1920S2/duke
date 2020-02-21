package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.exception.MissingTaskException;


public class FindCommand extends Command {
    private final String FIND_COMMAND = "find";

    public FindCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingTaskException {
        if (!userInput.trim().equals(FIND_COMMAND)) {
            tasks.findTask(userInput.substring(FIND_COMMAND.length() + 1));
        } else {
            throw new MissingTaskException("Please specify a word or phrase to search for!");
        }

    }


}
