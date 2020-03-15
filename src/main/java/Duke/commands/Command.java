package duke.commands;

import duke.exceptions.MissingDateTimeException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.MissingLocationException;
import duke.exceptions.WhitespaceExceptions;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.io.IOException;
import java.util.ArrayList;

public interface Command {

    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks)
            throws MissingDescriptionException, MissingLocationException, MissingDateTimeException, WhitespaceExceptions, IOException;

}
