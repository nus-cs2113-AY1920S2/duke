package duke.commands;

import java.time.LocalDate;
import java.util.ArrayList;

import static duke.data.TaskList.filterDate;
import static duke.data.TaskList.filterDateAfter;
import static duke.data.TaskList.filterDateBefore;
import static duke.ui.Messages.FIND_MESSAGE;
import static duke.ui.Messages.UNKNOWN_TIME_SPECIFIER_MESSAGE;


public class DueCommand extends Command {
    public static final String COMMAND_WORD = "due";
    public static final String FORMAT = "due (before/after/on) <date/time-term>";

    private LocalDate searchDate;
    private String timeSpecifier;

    public DueCommand(LocalDate date, String specifier) {
        searchDate = date;
        timeSpecifier = (specifier != null) ? specifier.toLowerCase() : "on";
    }

    public DueCommand(LocalDate date) {
        searchDate = date;
        timeSpecifier = "on";
    }

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
