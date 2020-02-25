package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    /** Stores information about the time of the task's deadline */
    private String deadlineTime;
    /** Stores information about the date of the task's deadline */
    private LocalDate deadlineDate;

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
        String dateWithTime = getDeadline(descriptionWithDeadline);
        String[] splitDeadline = dateWithTime.split(" ",2);
        this.deadlineDate = LocalDate.parse(splitDeadline[0]);
        this.deadlineTime = splitDeadline[1];
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
        return "(by: " + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " +  deadlineTime + " Hrs )";
    }
    /**
     * Returns the deadline of the task in the following format.<br>
     * Format : deadline
     *
     * @return deadline Represents the deadline of the task in the required format.
     */
    // Returns the deadline
    public String getDeadlineInInputFormat() {
        return deadlineDate + " " + deadlineTime;
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
