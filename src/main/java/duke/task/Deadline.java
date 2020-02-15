package duke.task;

import duke.task.Todo;

public class Deadline extends Todo {
    private String by;
    private final static char taskType = 'D';

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public char getTaskType() {
        return taskType;
    }

    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
