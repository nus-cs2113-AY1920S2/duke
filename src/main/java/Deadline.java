public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getDescription() {
        return description + " (by: " + dueDate + ")";
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

}
