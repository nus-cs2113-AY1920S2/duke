package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.TaskException;
import duke.exceptions.EventDateException;



public class EventCommand extends ExecuteCommand {

    public EventCommand(String userData) {
        this.userData = userData;
        this.toExit = false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws TaskException, EventDateException {
        if (!userData.trim().toLowerCase().equals("event")) {
            int indexOfAt = userData.indexOf("/at");
            if (indexOfAt == -1) {
                throw new EventDateException("Please specify a date for event by using \'at\'!");
            }
            String[] newData = userData.split(" /at ");
            if (newData.length == 1) {
                throw new EventDateException("Please specify a date after \'/at\'!");
            }
            tasks.eventTask(newData[0], newData[1]);
            storage.saveData(tasks);
        } else {
            throw new TaskException("Add a task behind 'event', task cannot be left empty.");
        }
    }
}
