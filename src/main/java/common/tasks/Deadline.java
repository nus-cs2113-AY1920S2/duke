package common.tasks;

public class Deadline extends Task {
    private final String deadline;
    
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    
    public Deadline(String description, String deadline, boolean isDone) {
        super(description);
        this.deadline = deadline;
        this.isDone = isDone;
    }
    
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description
            + "(by: " + this.deadline + ")";
    }
}
