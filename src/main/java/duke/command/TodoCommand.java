package duke.command;

import duke.exception.MissingTaskException;
import duke.Ui;
import duke.TaskList;


public class TodoCommand extends Command {
    private final String TODO_COMMAND = "todo";

    public TodoCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws MissingTaskException {
        if (!userInput.trim().equals(TODO_COMMAND)) {
            String todoTask = userInput.substring(TODO_COMMAND.length() + 1);
            tasks.addTodo(todoTask);
        } else {
            throw new MissingTaskException("Todo tasks cannot be empty!");
        }

    }




}
