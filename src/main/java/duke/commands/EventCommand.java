package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;
import duke.task.Tasks;
import duke.task.Event;
import duke.converter.timeConverter;

import java.text.ParseException;

import static duke.constants.Messages.*;

public class EventCommand extends Command {
    public EventCommand(String command) {
        super();
        this.command = command;
    }

    public void execute(TaskList taskList, Ui uiInput, Storage storage) throws DukeException, ParseException {
        String[] words = command.split(" ", 2);
        if (words.length == 1) {
            throw new DukeException(MESSAGE_EVENT_DESCRIPTION_CANNOT_BE_EMPTY);
        }
        else if (words[1].equals(" ") || (words[1].equals(""))) {
            throw new DukeException(MESSAGE_EVENT_DESCRIPTION_CANNOT_BE_EMPTY);
        }
        String[] wordSplitter = words[1].split("/at");
        if (wordSplitter[0].equals(" ") || wordSplitter[0].equals("")) {
            throw new DukeException(MESSAGE_EVENT_DESCRIPTION_CANNOT_BE_EMPTY);
        }
        String[] dateTime = wordSplitter[1].trim().split(" ");
        if (dateTime.length > 1) {
            Tasks task = new Event(wordSplitter[0].trim(), timeConverter.dateTime(wordSplitter[1].trim()));
            taskList.addTasks(task);
            storage.writeFile(taskList);
        }
        else {
            throw new DukeException(MESSAGE_TIME_EVENT_CANNOT_BE_EMPTY);
        }
    }
    public boolean isExit() {
        return false;
    }
}
