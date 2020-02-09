package types;

public class Todo extends Task {

    /**
     * A task that needs to be done
     * @param description the task that needs to be done
     * @param taskNumber what number the task is on our list
     */
    public Todo(String description, int taskNumber) {
        super(description, taskNumber);
    }

    /**
     * The task in string form
     * @return string of task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
