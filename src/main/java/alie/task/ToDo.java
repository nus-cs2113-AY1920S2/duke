package alie.task;

public class ToDo extends Task {
    private static final String TODO_SYMBOL = "T";

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getTaskInfo() {
        return ("[T]" + super.getTaskInfo());
    }

    @Override
    public String encodeTask() {
        return TODO_SYMBOL + "|" + isDone + "|" + description;
    }

    public static ToDo decodeTask(String encodedTask) {
        String[] task = encodedTask.split("\\" + DELIMITER);
        boolean isDone = Boolean.parseBoolean(task[1]);
        String description = task[2];
        ToDo toDoTask = new ToDo(description);
        if (isDone) {
            toDoTask.setIsCompleted();
        }
        return toDoTask;
    }
}
