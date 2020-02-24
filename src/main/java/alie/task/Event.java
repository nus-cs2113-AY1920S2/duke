package alie.task;

public class Event extends Task {
    private static final String EVENT_SYMBOL = "E";
    private String startDate;

    public Event(String name, String date) {
        super(name);
        startDate = date;
    }

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
