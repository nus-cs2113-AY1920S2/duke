package alie.task;

public class Deadlines extends Task {
    private String endDate;

    public Deadlines(String name, String date) {
        super(name);
        endDate = date;
    }

    @Override
    public String getTaskInfo() {
        return ("[D]" + super.getTaskInfo() + " (by: " + endDate + ")");
    }
}
