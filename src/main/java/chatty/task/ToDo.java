package chatty.task;

import static chatty.util.Constants.FILE_FIELD_SEPARATOR_FOR_WRITE;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getFileString() {
        return "T" + FILE_FIELD_SEPARATOR_FOR_WRITE + this.isDone + FILE_FIELD_SEPARATOR_FOR_WRITE + this.description;
    }
}
