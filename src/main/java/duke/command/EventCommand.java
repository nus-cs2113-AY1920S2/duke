package duke.command;

import duke.Ui;
import duke.exception.MissingTaskException;
import duke.exception.MissingEventDateException;
import duke.TaskList;


public class EventCommand extends Command {
    private final String EVENT_COMMAND = "event";

    public EventCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws MissingTaskException, MissingEventDateException {
        if (!userInput.trim().equals(EVENT_COMMAND)) {
            int indexOfAt = userInput.indexOf("/at");
            if (indexOfAt == -1) {
                throw new MissingEventDateException("Please specify the date for event using \'at\'!");
            }
            String eventTask = userInput.substring(EVENT_COMMAND.length() + 1, indexOfAt - 1);
            String atDate = userInput.substring(indexOfAt + "/at".length() + 1);
            tasks.addEvent(eventTask, atDate);
        } else {
            throw new MissingTaskException("Event tasks cannot be empty!");
        }
    }
}
