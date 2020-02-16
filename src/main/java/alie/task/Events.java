package alie.task;

public class Events extends Task {
    private static final String EVENT_SYMBOL = "E";
    private String startDate;

    public Events (String name, String date) {
        super(name);
        startDate = date;
    }

    @Override
    public String getTaskInfo() {
        return ("[E]" + super.getTaskInfo() + " (at: " + startDate + ")");
    }

    @Override
    public String encodeTask() {
        return EVENT_SYMBOL + "|" + isDone + "|" + description + "|" + startDate;
    }

    public static Events decodeTask(String encodedTask) {
        String[] task = encodedTask.split(DELIMITER);
        boolean isDone = Boolean.parseBoolean(task[1]);
        String description = task[2];
        String startDate = task[3];
        Events eventTask = new Events(description, startDate);
        if (isDone) {
            eventTask.setTaskCompleted(eventTask);
        }
        return eventTask;
    }
}
