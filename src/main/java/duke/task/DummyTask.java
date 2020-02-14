package duke.task;

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
    private String atTime;
    private String byTime;

    public String getIcon() {
        return typeIcon;
    }

    public String getAtTime() {
        return atTime;
    }

    public String getByTime() {
        return byTime;
    }

    public String getStatusIcon() {
        return (isDone ? YES_ICON : NO_ICON); //return tick or X symbols
    }

    public String getTaskDescription() {
        return description;
    }
}
