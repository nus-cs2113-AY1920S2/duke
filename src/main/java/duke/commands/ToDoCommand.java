package duke.commands;

import duke.tasklist.TaskList;
import duke.exceptions.BadLineFormatException;
import duke.tasks.ToDo;

import java.util.Arrays;

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

    public void execute() {
        taskList.addTask(toDo);
    }
}
