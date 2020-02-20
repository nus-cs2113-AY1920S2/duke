package Duke.TaskTypes;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The <code>TODO</code> type class
 * @see Task
 */
public class Todo extends Task {

    protected String by;
    protected LocalDate actualTime;
    protected String slashWord;

    public Todo(String description, String by) {
        super(description);
        this.by = by;
        if (!this.by.equals("")) {
            String[] bySplit = by.split(" ", 2);
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
        return "[T]" + super.toString() + "(" + this.slashWord + ": " +
                this.actualTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Gets the <code>TODO</code> information stored nicely in a <code>String[]</code> array
     * <p></p>
     * <p>
     * Used in the {@link Duke.Storage} class for retrieving the <code>TODO</code> task's information to store it as
     * offline saved data
     * </p>
     * @return a <code>String[]</code> array containing the description, the time and whether the event is done
     * @see Duke.Storage#save
     */
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
