package alie.task;

/**
 * Task that begin at a specific date or timing.
 */
public class Event extends Task {
    private static final String EVENT_SYMBOL = "E";
    private String startDate;

    /**
     * Default constructor to initialise task's name and due date.
     * @param name Name of task.
     * @param date Date when task begins.
     */
    public Event(String name, String date) {
        super(name);
        startDate = date;
    }

    /**
     * Getter to obtain due date of task.
     * @return String containing due date of task.
     */
    public String getDate() {
        return startDate;
    }

    @Override
    public String getTaskInfo() {
        return ("[E]" + super.getTaskInfo() + " (at: " + startDate + ")");
    }

    @Override
    public String encodeTask() {
        return EVENT_SYMBOL + DELIMITER + isDone + DELIMITER + description + DELIMITER + startDate;
    }

    /**
     * Decipher a string containing information of a task (event)
     * @param encodedTask String containing information of the task (event)
     * @return Deadline object with information from encodedTask.
     */
    public static Event decodeTask(String encodedTask) {
        String[] task = encodedTask.split("\\" + DELIMITER);
        boolean isDone = Boolean.parseBoolean(task[1]);
        String description = task[2];
        String startDate = task[3];
        Event eventTask = new Event(description, startDate);
        if (isDone) {
            eventTask.setTaskCompleted(eventTask);
        }
        return eventTask;
    }
}
