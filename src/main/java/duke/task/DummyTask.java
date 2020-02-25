package duke.task;

import java.time.LocalDate;

import static duke.util.Constants.NO_ICON;
import static duke.util.Constants.YES_ICON;

/*
 * This is a compromised way of converting json strings of different objects into their
 * respective actual objects by creating an `super` objects which contains all possible attributes
 * so as to prevent any loss of attributes after conversion. If there is any better way, please tell me.
 */
public class DummyTask {
    private String description;
    private boolean isDone;
    private String typeIcon;
    private LocalDate atDate;
    private LocalDate byDate;

    public String getIcon() {
        return typeIcon;
    }

    public LocalDate getAtDate() {
        return atDate;
    }

    public LocalDate getByDate() {
        return byDate;
    }

    public String getStatusIcon() {
        return (isDone ? YES_ICON : NO_ICON); //return tick or X symbols
    }

    public String getTaskDescription() {
        return description;
    }
}
