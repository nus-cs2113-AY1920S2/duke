package duke.taskmanager;

public class Deadline extends Tasks {
    protected String by;
    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public String getTask() {
        return task + by;
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[D]" + super.toString();
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String contentToFile() { return "D" + "|" +
            super.contentToFile() + "|" + by;
    }
}
