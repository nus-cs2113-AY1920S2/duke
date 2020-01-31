public class Todo extends Task {
    protected  String typeIcon;

    public Todo(String description) {
        super(description);
        this.typeIcon = "[T]";
    }

    @Override
    public String toString() {
        return typeIcon + super.toString();
    }
}
