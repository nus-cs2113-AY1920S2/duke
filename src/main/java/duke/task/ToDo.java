package duke.task;

public class ToDo extends Task {

    // Constructor
    public ToDo(String description) {
        super(description);
        taskType = 'T';
    }

    // Returns the task's type and status along with it's description as a string
    @Override
    public String getStatusWithDescription() {
        return "[" + this.taskType + "]" + super.getStatusWithDescription();
    }

}
