package duke.task;

import duke.time.Time;

public class Todo extends Time {
    private String description;
    private boolean isDone;
    private final static char taskType = 'T';

    public Todo(String description) {
        this.isDone = false;
        this.description = description;
    }

    private String isDone() {
        return this.isDone ? "[✓] " : "[✗] ";
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isItDone() {
        return this.isDone;
    }
    public void setDone() {
        this.isDone = true;

    }

    public char getTaskType() {
        return taskType;
    }

    public String toString() {
        return String.format("[%c]", getTaskType()) + isDone() + this.description;
    }
}