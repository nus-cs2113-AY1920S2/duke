public class Event extends Task {
    //protected String at;

    public Event(String action, String at) {
        super(action);
        this.taskType = "E";
        this.date = at;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + "(at: " + date + ")";
    }
}
