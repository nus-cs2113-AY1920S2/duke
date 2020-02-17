package Duke.TaskTypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Todo extends Task {

    protected String by;
    protected LocalDate actualTime;
    protected String slashWord;

    public Todo(String description, String by) {
        super(description);
        this.by = by;
        String[] bySplit = by.split(" ", 2);
        if (bySplit.length > 1) {
            this.slashWord = bySplit[0].trim();
            String tempTime = bySplit[1].trim();
            this.actualTime = LocalDate.parse(tempTime);
        }
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[T]" + super.toString();
        }
        return "[T]" + super.toString() + "(" + this.slashWord + ": " + this.actualTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String[] getTaskInfo() {
        String[] taskInfoArray = new String[4];
        taskInfoArray[0] = "T";
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
