package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Deals with command that can check deadline/event by time.
 */
public class CheckCommand extends Command {

    /** User input time.*/
    private static String checkString;
    /** Stores the task index that matches.*/
    private static ArrayList<Integer> checkCount;

    public CheckCommand(String checkString) {
        this.checkString = checkString;
        checkCount = new ArrayList<>();
    }

    /**
     * Checks if a string follows an identified time format.
     *
     * @return True if it follows, false if it does not follow.
     */
    private Boolean isLocalDateFormat() {
        try {
            LocalDate.parse(this.checkString);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks tasks occuring on a specific date.
     *
     * @param tasks Stores all tasks.
     * @param ui Deals with user interface.
     * @param storage Deals with back up file.
     * @throws DukeException If the format of time is not correct.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!isLocalDateFormat()) {
            throw new DukeException("\t Please follow the format: check yyyy-mm-dd");
        }
        LocalDate date = LocalDate.parse(checkString);
        for (int i = 0; i < tasks.size(); i++) {
            if ((tasks.getATask(i).getTime() == null)) {
                /** either it is a todo event or the time does not follow standard */
                continue;
            }
            if (tasks.getATask(i).getTime().equals(date)) {
                checkCount.add(i);
            }
        }
        ui.showFindTask(tasks,checkCount);
    }
}
