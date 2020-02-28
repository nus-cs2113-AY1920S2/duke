package duke.task;

import java.time.LocalDate;

import static duke.util.Constants.NO_ICON;
import static duke.util.Constants.YES_ICON;

/**
 * This is a compromised way of converting json strings of different objects into their
 * respective actual objects by creating an `super` objects which contains all possible attributes
 * so as to prevent any loss of attributes after conversion. If there is any better way, please tell me.
 *
 * @author A11riseforme
 */
public class DummyTask {
    private String description;
    private boolean isDone;
    private String typeIcon;
    private LocalDate atDate;
    private LocalDate byDate;

    /**
     * Get the Icon representation of the event task.
     *
     * @return a icon string which represents the event task. `[E]` for event, `[D]` for deadline, `[D]` for todo.
     */
    public String getIcon() {
        return typeIcon;
    }

    /**
     * Get the date of the event task.
     *
     * @return a LocalDate of the date of the event task.
     */
    public LocalDate getAtDate() {
        return atDate;
    }

    /**
     * Get the date of the deadline task.
     *
     * @return a LocalDate of the date of the deadline task.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Get the icon representation for the task status. `[v]` for not done, `[v]` for done.
     *
     * @return a String of icon.
     */
    public String getStatusIcon() {
        return (isDone ? YES_ICON : NO_ICON);
    }

    /**
     * Cast the task to String type by returning a string of its description.
     *
     * @return the description of the task in String.
     */
    public String getTaskDescription() {
        return description;
    }
}
