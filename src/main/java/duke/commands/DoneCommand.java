package duke.commands;

import duke.tasklist.TaskList;
import duke.exceptions.BadLineFormatException;
import duke.exceptions.BadTaskChoiceFormatException;

public class DoneCommand extends TaskSelectionCommand {
    public static final String EXAMPLE_USAGE = "done <Task Number>";
    public static final String KEYWORD = "done";

    public DoneCommand(String keyword, String[] tokens, TaskList taskList) throws BadLineFormatException {
        super(keyword, tokens, taskList);
    }

    public void execute() throws BadTaskChoiceFormatException {
        int taskIndex = getTaskIndex();
        taskList.markAsDone(taskIndex);
    }
}
