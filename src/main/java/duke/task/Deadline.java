package duke.task;

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String getDateTime() {
        return deadline;
    }

    @Override
    public String getTaskStatus() {
        return "[D]" + super.getTaskStatus() + " (by: " + deadline + ")";
    }
}
