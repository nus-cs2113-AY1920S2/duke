package duke.commands;

import duke.data.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

import static duke.data.TaskList.filterDate;
import static duke.data.TaskList.filterDateAfter;
import static duke.data.TaskList.filterDateBefore;
import static duke.ui.Messages.FIND_MESSAGE;
import static duke.ui.Messages.UNKNOWN_TIME_SPECIFIER_MESSAGE;

/**
 * <h3>Due Command</h3>
 * A <b>Command</b> to show a filtered <b>Task List</b> to the user based on the <i>keyword</i> provided. The
 * <i>filtered list</i> contains <b>Deadline</b> and <b>Event</b> tasks that have their <i>date</i> <i>on</i>,
 * <i>before</i> or <i>after</i> the specified <i>date/i> provided by the user.
 * @see Command
 * @see TaskList
 * @see duke.task.Deadline
 * @see duke.task.Event
 */
public class DueCommand extends Command {
    public static final String COMMAND_WORD = "due";
    public static final String FORMAT = "due (on/before/after) <date/time-term>";

    private LocalDate searchDate;
    private String timeSpecifier;

    /**
     * Constructs the <b>Due Command</b>.
     * <br> If the <code>specifier</code> given is <code>NULL</code>, the <code>timeSpecifier</code> is set to
     * <i>on</i>.
     * @param date The <i>date</i> to filter the <b>Task List</b> by
     * @param specifier The <i>time specifier</i> to filter the <b>Task List</b> by
     */
    public DueCommand(LocalDate date, String specifier) {
        searchDate = date;
        timeSpecifier = (specifier != null) ? specifier.toLowerCase() : "on";
    }

    /**
     * Executes the <b>Due Command</b> to show the filtered <b>Task List</b> to the user based on the
     * <code>searchDate</code> and <code>timeSpecifier</code>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see TaskList
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        ArrayList<Integer> searchedTaskIndices;
        switch (timeSpecifier) {

        case "on":
            searchedTaskIndices = filterDate(searchDate);
            return new CommandResult(FIND_MESSAGE, true, searchedTaskIndices);
        case "before":
        case "b":
            searchedTaskIndices = filterDateBefore(searchDate);
            return new CommandResult(FIND_MESSAGE, true, searchedTaskIndices);
        case "after":
        case "a":
            searchedTaskIndices = filterDateAfter(searchDate);
            return new CommandResult(FIND_MESSAGE, true, searchedTaskIndices);
        default:
            return new CommandResult(UNKNOWN_TIME_SPECIFIER_MESSAGE);
        }

    }
}
