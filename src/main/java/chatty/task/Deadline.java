package chatty.task;

import static chatty.util.Constants.FILE_FIELD_SEPARATOR_FOR_WRITE;

public class Deadline extends Task {

    protected String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
    }

    @Override
    public String getFileString() {
        return "D" + FILE_FIELD_SEPARATOR_FOR_WRITE + this.isDone + FILE_FIELD_SEPARATOR_FOR_WRITE + this.description +
                FILE_FIELD_SEPARATOR_FOR_WRITE + this.dateTime;
    }
}
