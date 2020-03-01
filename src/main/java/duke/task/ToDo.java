package duke.task;

import duke.format.DateTime;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    @Override
    public DateTime getDateTime() {
        return null;
    }

    @Override
    public String getTaskStatus() {
        return "[T]" + super.getTaskStatus();
    }
}
