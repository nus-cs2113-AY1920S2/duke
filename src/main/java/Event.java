public class Event extends Task {
    private String time;
    public Event (int taskID, String description, boolean isDone, String time) {
        super (taskID, description, isDone);
        this.time = time;
    }

    public String getTaskType () {
        return "[E]";
    }

    @Override
    public String toString () {
        return (super.taskID + 1) + ". [E]" + super.getStatusIcon() + " " + super.description + " (at: " + time + ")";
    }
}
