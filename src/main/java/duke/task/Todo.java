package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toData(int taskId) {
        String dataLine = taskId + "," + this.getType() + "," + this.isDone() + "," + this.getDescription();
        return dataLine;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}