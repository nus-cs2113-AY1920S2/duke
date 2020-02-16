package duke.taskmanager;

public class ToDo extends TaskManager {
    protected boolean isDone;
    public ToDo(String task) {
        super(task);
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}