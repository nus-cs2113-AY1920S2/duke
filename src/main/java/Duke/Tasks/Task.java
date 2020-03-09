package Duke.Tasks;

/**
 * Abstract class representing all the different types of tasks supported by program.
 */
public class Task {
    public String action;
    public boolean isDone;
    public String taskType;
    public String date;

    /**
     * Constructor for Task class.
     *
     * @param action the task description.
     */
    public Task(String action) {
        this.action = action;
        this.isDone = false;
    }

    /**
     * Creates a tick or cross status icon depending on whether task is completed.
     *
     * @return tick or cross status icon.
     */
    public String checkIfDone() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    /**
     * Marks completed tasks as done.
     *
     * @return isDone is true.
     */
    public boolean markAsDone() {
        return isDone = true;
    }

    /**
     * Abstract method that creates a String to indicate the task.
     *
     * @return String indicating task type, task description.
     */
    @Override
    public String toString() {
        return "[" + checkIfDone() + "] " + action;
    }
}
