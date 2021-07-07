package duke.task;

public class Todo extends Tasks {

    protected String type;

    public Todo (String description) {
        super(description, "T");

    }
    @Override
    public String getFileContents() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
