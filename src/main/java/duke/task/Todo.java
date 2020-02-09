package duke.task;

public class Todo extends Task {
    
    private final String TAG = "[T]";
    
    public Todo(String description) {
        super(description);
    }
    
    @Override
    public String toString() {
        return TAG + super.toString();
    }
    
}
