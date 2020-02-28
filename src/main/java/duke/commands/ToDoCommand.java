package duke.commands;

import duke.tasklist.TaskList;
import duke.exceptions.BadLineFormatException;
import duke.tasks.ToDo;

import java.util.Arrays;

/**
 * Class for a todo command that error checks user's input and can be executed to add the task to the
 * <code>TaskList</code>
 */
public class ToDoCommand extends Command {
    public static final String EXAMPLE_USAGE = "todo math homework";
    public static final String KEYWORD = "todo";
    public ToDo toDo;

    public ToDoCommand(String keyword, String[] tokens, TaskList taskList) throws BadLineFormatException {
        super(keyword, tokens, taskList);

        if (tokens.length < 2) {
            throw new BadLineFormatException("Input does not contain a description");
        }

        String description = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
        toDo = new ToDo(description);
    }

    /**
     * Adds the todo task to the referenced <code>TaskList</code>
     */
    public void execute() {
        taskList.addTask(toDo);
    }
}
