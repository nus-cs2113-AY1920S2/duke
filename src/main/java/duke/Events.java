package duke;

public class Events extends Task {

    protected String date;
    protected String time;

    public Events(String description, String date ,String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + " " + time +")";
    }
}

