package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "T";
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