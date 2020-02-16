package Duke.TaskTypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String by;
    protected LocalDate actualTime;
    protected String slashWord;

    public Event(String description, String by) {
        super(description);
        this.by = by;
        String[] bySplit = by.split(" ", 2);
        this.slashWord = bySplit[0].trim();
        String tempTime = bySplit[1].trim();
        this.actualTime = LocalDate.parse(tempTime);
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[E]" + super.toString();
        }
        String[] bySplit = by.split(" ", 2);
        return "[E]" + super.toString() + "(" + this.slashWord + ": " + this.actualTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String[] getTaskInfo() {
        String[] taskInfoArray = new String[4];
        taskInfoArray[0] = "E";
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
