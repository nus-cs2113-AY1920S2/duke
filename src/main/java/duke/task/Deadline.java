package duke.task;

import duke.format.DateTime;

public class Deadline extends Task {
    protected DateTime deadline;

    public Deadline(String task, DateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public DateTime getDateTime() {
        return deadline;
    }

    @Override
    public String getTaskStatus() {
        return "[D]" + super.getTaskStatus() + " (by: " + deadline + ")";
    }
}
