package duke.task;

import duke.exception.DukeNullDateException;
import duke.exception.DukeNullDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static duke.util.Constants.DEADLINE_ICON;

public class Deadline extends Task {
    protected LocalDate byDate;
    protected  String typeIcon;

    public Deadline(String description, LocalDate byDate) throws DukeNullDescriptionException {
        super(description);
        typeIcon = DEADLINE_ICON;
        this.byDate = byDate;
    }

    public String getIcon() {
        return typeIcon;
    }

    public LocalDate getByDate() {
        return byDate;
    }

    @Override
    public String toString() {
        return typeIcon + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
