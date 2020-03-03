package Duke.Tasks;

public class Event extends Task {

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
