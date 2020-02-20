package duke.command;

import duke.exception.MissingTaskException;
import duke.exception.MissingDeadlineDateException;
import duke.TaskList;
import duke.Storage;



public class DeadlineCommand extends Command {
    private final String DEADLINE_COMMAND = "deadline";

    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingTaskException, MissingDeadlineDateException {
        if (!userInput.trim().equals(DEADLINE_COMMAND)) {
            int indexOfBy = userInput.indexOf("/by");
            if (indexOfBy == -1) {
                throw new MissingDeadlineDateException("Please specify a deadline using \'/by\'!");
            }
            String deadlineTask = userInput.substring(DEADLINE_COMMAND.length() + 1, indexOfBy - 1);
            String byDate = userInput.substring(indexOfBy + "/by".length() + 1);
            tasks.addDeadline(deadlineTask, byDate);
            storage.save(tasks);
        } else {
            throw new MissingTaskException("Deadline tasks cannot be empty!");
        }
    }


}
