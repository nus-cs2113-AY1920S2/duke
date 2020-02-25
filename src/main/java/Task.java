/**
 * Represents the task objects that are tracked
 * by Duke. A task object is the parent class for the deadline,
 * event and todo objects.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the task object. The constructor
     * initialises the isDone to be false to show that
     * the task is not yet completed.
     * @param description information about the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter for the status of the task.
     * @return a string that symbolises a tick when the task is completed
     * and a cross when otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Method used to get the description of the task for printing.
     * This method is overridden by child classes
     * which requires additional formatting/information.
     * @return a string that describes the task object
     */
    public String showFullDescription() {
        return description;
    }

    /**
     * Getter for the description of the Task.
     * @return a string that describes the task object
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the icon of the task object. As the task object
     * is a parent class, there is no icon for the task object.
     * This method is overridden by child classes.
     * @return a string that acts as a icon to differentiate
     * between the various types of tasks
     */
    public String getTypeIcon() {
        return "";
    }

    /**
     * Getter for the status of the task.
     * @return true if the task is done and false otherwise
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Setter to set the status of the task.
     * @param isDone boolean description on the status of the task
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Method to mark task as done. This method sets
     * isDone to be true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Override the default method to provide a more meaningful string
     * representation on the description of the task.
     * @return a formatted string that describes the tasks in more details
     */
    @Override
    public String toString() {
        String status = String.format("[%s]", this.getStatusIcon());
        return this.getTypeIcon() + status + " " + this.showFullDescription();
    }
}
