package Duke.Tasks;

public class Todo extends Task {

    public Todo(String action) {
        super(action);
        this.taskType = "T";
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString();
    }
}
