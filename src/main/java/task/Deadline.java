package task;

public class Deadline extends Task {
    protected String byTime;
    protected  String typeIcon;

    public Deadline(String description, String byTime) {
        super(description);
        typeIcon = "[D]";
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return typeIcon + super.toString() + " (by: " + byTime + ")";
    }
}
