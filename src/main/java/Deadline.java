public class Deadline extends Task {
    //protected String by;

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
