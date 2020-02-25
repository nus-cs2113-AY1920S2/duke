package duke.task;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    /** Stores information about deadline of task */
    private String by;

    /**
     * Constructor for Deadline Task Class.
     * <p> <br>
     * It creates a new Deadline task with the description provided by the user.
     * It also sets the taskType to 'D', denoting that the task is a Deadline task.
     *</p>
     * @param descriptionWithDeadline Contains both the description and the deadline of the task to be created.
     */
    public Deadline(String descriptionWithDeadline) {
        super(getDescription(descriptionWithDeadline));
        taskType = 'D';
        this.by = getDeadline(descriptionWithDeadline);
    }

    /**
     * Abstracts out the description from the user given input and returns it.
     *
     * @param descriptionWithDeadline Contains both the description and the deadline of the task to be created.
     * @return description Contains the description of the task.
     */
    private static String getDescription(String descriptionWithDeadline) {
        String description;
        description = descriptionWithDeadline.split("/",2)[0];
        return description;
    }

    /**
     * Abstracts out the deadline from the user given input and returns it.
     *
     * @param descriptionWithDeadline Contains both the description and the deadline of the task to be created.
     * @return description Contains the description of the task.
     */
    private static String getDeadline(String descriptionWithDeadline) {
        String deadline;
        deadline = (descriptionWithDeadline.split("/",2)[1].split(" ", 2))[1];
        return deadline;
    }

    /**
     * Returns the deadline of the task in the following format.<br>
     * Format : (by: deadline)
     *
     * @return deadline Represents the deadline of the task in the required format.
     */
    public String getBy() {
        String deadline;
        deadline = "(by: " + by + ")";
        return deadline;
    }

    /**
     * Returns the deadline of the task in the following format.<br>
     * Format : deadline
     *
     * @return deadline Represents the deadline of the task in the required format.
     */
    public String getByWithoutBraces() {
        String deadline;
        deadline = this.by;
        return deadline;
    }

    /**
     * Returns the status, description and deadline of the task.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return taskInformation Represents the required information related to the task.
     */
    @Override
    public String getStatusWithDescription() {
        String taskInformation;
        taskInformation = "[" + this.taskType + "]" + super.getStatusWithDescription() + getBy();
        return taskInformation;
    }
}
