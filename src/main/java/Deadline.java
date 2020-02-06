public class Deadline extends Task {
    private String time;
    public Deadline (int taskID, String description, boolean isDone, String time) {
        super (taskID, description, isDone);
        this.time = time;
    }

    public String getTaskType () {
        return "[D]";
    }

    @Override
    public String toString () {
        return (super.taskID + 1) + ". [D]" + super.getStatusIcon() + " " + super.description + " (by: " + time + ")";
    }
}
