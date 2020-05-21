package task;

/**
 * Parent entity class for tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns Done or Not Done status
     *
     * @return status
     */
    public String getStatus() {
        return (isDone ? "Done" : "Not Done");
    }

    /**
     * Updates task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[" + getStatus() + "]  " + this.description);
    }

    /** Converts task to data format. */
    public String convertToData() {
        int isDoneAsInt = isDone ? 1 : 0;
        return String.format(isDoneAsInt + "|" + this.description);
    }
}
