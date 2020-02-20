package duke.command;

import duke.TaskList;
import duke.exception.MissingTaskNumberDescriptionException;
import duke.exception.MissingTaskNumberException;
import duke.Storage;

public class DeleteCommand extends Command {
    private final String DELETE_COMMAND = "delete";

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingTaskNumberException, MissingTaskNumberDescriptionException {
        if (!userInput.trim().equals(DELETE_COMMAND)) {
            String[] words = userInput.split(" ");
            int taskNum = Integer.parseInt(words[1]);
            if(taskNum <= tasks.getListSize()) {
                tasks.deleteTask(taskNum);
                storage.save(tasks);
            } else {
                throw new MissingTaskNumberException("This task number does not exist on the list!");
            }
        } else {
            throw new MissingTaskNumberDescriptionException("Please add a task number to \'delete\' to delete a task!");
        }
    }
}
