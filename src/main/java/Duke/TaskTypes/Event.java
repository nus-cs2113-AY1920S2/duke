package Duke.TaskTypes;

public class Event extends Task {

    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[E]" + super.toString();
        }
        String[] bySplit = by.split(" ", 2);
        return "[E]" + super.toString() + "(" + bySplit[0] + ": " + bySplit[1] + ")";
    }

    @Override
    public String[] getTaskInfo() {
        String[] taskInfoArray = new String[4];
        taskInfoArray[0] = "E";
        if (super.isDone)
        {
            taskInfoArray[1] = "1";
        }
        else
        {
            taskInfoArray[1] = "0";
        }
        taskInfoArray[2] = super.description;
        taskInfoArray[3] = by;
        return taskInfoArray;


    }
}
