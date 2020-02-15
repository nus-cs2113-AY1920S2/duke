package task;

public class Event extends Task {

    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.time + ")";
    }

    @Override
    public String saveTask() {
        return "E | " + super.isDoneNum() + " | " + super.saveTask() + " | " + this.time;
    }
}
