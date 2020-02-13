public class ToDo extends Task {

    protected String description;
    protected boolean isDone;

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description , isDone);
    }
    @Override
    public String toString() {
        return "[T][ " + super.getStatusIcon() + " ] " + super.getDescription();
    }

    @Override
    public String storeText() {
        return "[T]," + super.getStatus() + "," + super.getDescription() + ",";
    }
}
