package alie.task;

/**
 * Task that should be completed before a specific date or time.
 */
public class Deadline extends Task {
    protected static final String DEADLINE_SYMBOL = "D";
    private String endDate;

    /**
     * Default constructor to initialise task's name and due date.
     * @param name Name of task.
     * @param date Due Date of task.
     */
    public Deadline(String name, String date) {
        super(name);
        endDate = date;
    }

    @Override
    public String getTaskInfo() {
        return ("[D]" + super.getTaskInfo() + " (by: " + endDate + ")");
    }

    @Override
    public String encodeTask() {
        return DEADLINE_SYMBOL + DELIMITER + isDone + DELIMITER + description + DELIMITER + endDate;
    }

    /**
     * Decipher a string containing information of a task (deadline)
     * @param encodedTask String containing information of the task (deadline)
     * @return Deadline object with information from encodedTask.
     */
    public static Deadline decodeTask(String encodedTask) {
        String[] task = encodedTask.split("\\" + DELIMITER);
        boolean isDone = Boolean.parseBoolean(task[1]);
        String description = task[2];
        String endDate = task[3];
        Deadline deadlineTask = new Deadline(description, endDate);
        if (isDone) {
            deadlineTask.setTaskCompleted(deadlineTask);
        }
        return deadlineTask;
    }
}
