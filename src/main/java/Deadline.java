package src.main.java;

public class Deadline extends Task {

    private final String DEADLINE = "D";
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        this.taskType = DEADLINE;
    }

    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (by: " + date + ")";
    }
}
