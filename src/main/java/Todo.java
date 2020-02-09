public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String showFullDescription() {
        return description;
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }
}
