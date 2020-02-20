package duke.command;

import duke.TaskList;
import duke.exception.MissingTaskNumberDescriptionException;
import duke.exception.MissingTaskNumberException;
import duke.Storage;

public class DoneCommand extends Command {
    private final String DONE_COMMAND = "done";

    public DoneCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingTaskNumberException, MissingTaskNumberDescriptionException {
        if (!userInput.trim().equals(DONE_COMMAND)) {
            String[] words = userInput.split(" ");
            int taskNum = Integer.parseInt(words[1]);
            int sizeOfArray = tasks.getListSize();
            if (taskNum <= sizeOfArray) {
                tasks.markDone(taskNum);
                storage.save(tasks);
            } else {
                throw new MissingTaskNumberException("This task number does not exist on the list!");
            }
        } else {
            throw new MissingTaskNumberDescriptionException("Please add a task number to \'done\' to mark task as done!");
        }

    }


}
