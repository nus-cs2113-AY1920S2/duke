package duke.task;

import duke.task.Todo;

public class Event extends Todo {
    private String at;
    private final static char taskType = 'E';

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public char getTaskType() {
        return taskType;
    }

    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
