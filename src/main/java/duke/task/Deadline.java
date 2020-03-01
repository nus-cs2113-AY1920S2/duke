package src.main.java.duke.task;

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

    public String writeInFile() { return super.writeInFile() + " | " + date;}
}
