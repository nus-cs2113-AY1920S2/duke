package alie.task;

/**
 * Task that need to be done.
 */
public class ToDo extends Task {
    private static final String TODO_SYMBOL = "T";

    /**
     * Default constructor to initialise task's name.
     * @param name Name of task.
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getTaskInfo() {
        return ("[T]" + super.getTaskInfo());
    }

    @Override
    public String encodeTask() {
        return TODO_SYMBOL + DELIMITER + isDone + DELIMITER + description;
    }

    /**
     * Decipher a string containing information of a task (todo)
     * @param encodedTask String containing information of the task (todo)
     * @return Deadline object with information from encodedTask.
     */
    public static ToDo decodeTask(String encodedTask) {
        String[] task = encodedTask.split("\\" + DELIMITER);
        boolean isDone = Boolean.parseBoolean(task[1]);
        String description = task[2];
        ToDo toDoTask = new ToDo(description);
        if (isDone) {
            toDoTask.setTaskCompleted(toDoTask);
        }
        return toDoTask;
    }
}
