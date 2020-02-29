package duke.task.tasktypes;

public class Deadline extends Task {

    private String by;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;

        this.taskType = TaskType.D;
    }

    public String getBy () {
        return by;
    }

    public void setBy () {
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", taskType, super.toString(), by);
    }
}
