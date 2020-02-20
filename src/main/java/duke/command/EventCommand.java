package duke.command;

import duke.exception.MissingTaskException;
import duke.exception.MissingEventDateException;
import duke.TaskList;
import duke.Storage;


public class EventCommand extends Command {
    private final String EVENT_COMMAND = "event";

    public EventCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws MissingTaskException, MissingEventDateException {
        if (!userInput.trim().equals(EVENT_COMMAND)) {
            int indexOfAt = userInput.indexOf("/at");
            if (indexOfAt == -1) {
                throw new MissingEventDateException("Please specify the date for event using \'at\'!");
            }
            String eventTask = userInput.substring(EVENT_COMMAND.length() + 1, indexOfAt - 1);
            String atDate = userInput.substring(indexOfAt + "/at".length() + 1);
            tasks.addEvent(eventTask, atDate);
            storage.save(tasks);
        } else {
            throw new MissingTaskException("Event tasks cannot be empty!");
        }
    }
}
