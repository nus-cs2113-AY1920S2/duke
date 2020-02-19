public class Todo extends Task {
    //protected String by;

    public Todo(String action) {
        super(action);
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString();
    }
}
