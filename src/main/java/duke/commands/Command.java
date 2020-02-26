package duke.commands;

import duke.tasklist.TaskList;
import duke.exceptions.BadTaskChoiceFormatException;

public abstract class Command {
    protected String keyword;
    protected String[] tokens;
    protected TaskList taskList;
    protected boolean isPersistentCommand = true;

    public Command(String keyword, String[] tokens, TaskList taskList) {
        this.keyword = keyword;
        this.tokens = tokens;
        this.taskList = taskList;
    }

    public boolean getIsPersistentCommand() {
        return isPersistentCommand;
    }

    public abstract void execute() throws BadTaskChoiceFormatException;
}
