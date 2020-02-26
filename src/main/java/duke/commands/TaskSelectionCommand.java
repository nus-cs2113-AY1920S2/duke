package duke.commands;

import duke.exceptions.BadLineFormatException;
import duke.exceptions.BadTaskChoiceFormatException;
import duke.tasklist.TaskList;

public abstract class TaskSelectionCommand extends Command {
    public TaskSelectionCommand(String keyword, String[] tokens, TaskList taskList) throws BadLineFormatException {
        super(keyword, tokens, taskList);
        if (tokens.length <= 1) {
            throw new BadLineFormatException("Specify a task by entering a task number");
        } else if (tokens.length > 2) {
            throw new BadLineFormatException("Too many tokens");
        }
    }

    protected int getTaskIndex() throws BadTaskChoiceFormatException {
        try {
            return Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BadTaskChoiceFormatException("Task number is not parsable");
        }
    }
}
