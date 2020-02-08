package task;

public class Events extends Task {
    private String startDate;

    public Events (String name, String date) {
        super(name);
        startDate = date;
    }

    public String getTaskInfo() {
        return ("[E]" + super.getTaskInfo() + " (at: " + startDate + ")");
    }
}
