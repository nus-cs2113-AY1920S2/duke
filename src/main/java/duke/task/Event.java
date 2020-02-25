package duke.task;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    /** Stores information about period of task. */
    private String period;

    /**
     * Constructor for Event Task Class.
     * <p> <br>
     * It creates a new Event task with the description provided by the user.
     * It also sets the taskType to 'E', denoting that the task is an Event task.
     *</p>
     * @param descriptionWithPeriod Contains both the description and the period of the event to be created.
     */
    public Event(String descriptionWithPeriod) {
        super(getDescription(descriptionWithPeriod));
        taskType = 'E';
        this.period = getPeriod(descriptionWithPeriod);
    }

    /**
     * Abstracts out the description from the user given input and returns it.
     *
     * @param descriptionWithPeriod Contains both the description and the period of the event to be created.
     * @return description Contains the description of the task.
     */
    private static String getDescription(String descriptionWithPeriod) {
        String[] split = descriptionWithPeriod.split("/",2);
        return split[0];
    }

    /**
     * Abstracts out the period from the user given input and returns it.
     *
     * @param descriptionWithPeriod Contains both the description and the period of the event to be created.
     * @return description Contains the description of the task.
     */
    private static String getPeriod(String descriptionWithPeriod) {
        String[] split = descriptionWithPeriod.split("/",2);
        return (split[1].split(" ",2))[1];
    }

    /**
     * Returns the period of the task in the following format.<br>
     * Format : (at: period)
     *
     * @return formattedPeriod Represents the period of the task in the required format.
     */
    public String getPeriod() {
        String formattedPeriod;
        formattedPeriod = "(at: " + period + ")";
        return formattedPeriod;
    }

    /**
     * Returns the period of the task in the following format.<br>
     * Format : period
     *
     * @return period Represents the period of the task in the required format.
     */
    public String getPeriodWithoutBraces() {
        String formattedPeriod;
        formattedPeriod = period;
        return formattedPeriod;
    }

    /**
     * Returns the status, description and period of the task.<br>
     * Overrides the corresponding method in the parent class.
     *
     * @return taskInformation Represents the required information related to the task.
     */
    @Override
    public String getStatusWithDescription() {
        String taskInformation;
        taskInformation = "[" + this.taskType + "]" + super.getStatusWithDescription() + getPeriod();
        return taskInformation;
    }
}
