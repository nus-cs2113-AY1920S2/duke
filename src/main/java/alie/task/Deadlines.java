package alie.task;

public class Deadlines extends Task {
    protected static final String DEADLINE_SYMBOL = "D";
    private String endDate;

    public Deadlines(String name, String date) {
        super(name);
        endDate = date;
    }

    @Override
    public String getTaskInfo() {
        return ("[D]" + super.getTaskInfo() + " (by: " + endDate + ")");
    }

    @Override
    public String encodeTask() {
        return DEADLINE_SYMBOL + "|" + isDone + "|" + description + "|" + endDate;
    }

    public static Deadlines decodeTask(String encodedTask) {
        String[] task = encodedTask.split(DELIMITER);
        boolean isDone = Boolean.parseBoolean(task[1]);
        String description = task[2];
        String endDate = task[3];
        Deadlines deadlineTask = new Deadlines(description, endDate);
        if (isDone) {
            deadlineTask.setIsCompleted();
        }
        return deadlineTask;
    }
}
