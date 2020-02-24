package alie.task;

public class Deadline extends Task {
    protected static final String DEADLINE_SYMBOL = "D";
    private String endDate;

    public Deadline(String name, String date) {
        super(name);
        endDate = date;
    }

    public String getDate() {
        return endDate;
    }

    @Override
    public String getTaskInfo() {
        return ("[D]" + super.getTaskInfo() + " (by: " + endDate + ")");
    }

    @Override
    public String encodeTask() {
        return DEADLINE_SYMBOL + DELIMITER + isDone + DELIMITER + description + DELIMITER + endDate;
    }

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
