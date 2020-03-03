package Duke.Tasks;

public class Deadline extends Task {

    public Deadline(String action, String by) {
        super(action);
        this.taskType = "D";
        this.date = by;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + "(by: " + date + ")";
    }
}
