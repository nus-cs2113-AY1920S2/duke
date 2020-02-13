package Duke.TaskTypes;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[D]" + super.toString();
        }
        String[] bySplit = by.split(" ", 2);
        return "[D]" + super.toString() + "(" + bySplit[0] + ": " + bySplit[1] + ")";
    }

    @Override
    public String[] getTaskInfo() {
        String[] taskInfoArray = new String[4];
        taskInfoArray[0] = "D";
        if (super.isDone) {
            taskInfoArray[1] = "1";
        } else {
            taskInfoArray[1] = "0";
        }
        taskInfoArray[2] = super.description;
        taskInfoArray[3] = by;
        return taskInfoArray;

    }
}
