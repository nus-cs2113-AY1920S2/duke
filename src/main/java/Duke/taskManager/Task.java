package duke.taskManager;

/**
 * The Task class store the record of the description of any Task created
 * @author Lim Yu Xiang
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Public constructor for Task and isDone is set to false by default.
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter method for getting status of the Task completion
     * @return O if task is done and X if it is not done
     */
    public String getStatusIcon(){
        if(isDone) {
            return "O";
        } else {
                return "X";
        }
    }

    /**
     * Getter method for getting descriptions of the Task
     * @return O if task is done and X if it is not done
     */
    public String getDescription(){
        return description;
    }

    /**
     * Mark the task as done by setting isDone to true.
     */
    public void markAsDone(Task description) {
        this.isDone = true;
    }

    /**
     * Import task as completed if it is completed
     */
    public void importDone() {
        this.isDone = true;
    }

    /**
     * Return a string representation of the task
     * @return [taskStatus] followed by the description
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "]" + getDescription();
    }

}
