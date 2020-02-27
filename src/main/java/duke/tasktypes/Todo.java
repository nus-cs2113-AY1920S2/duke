package duke.tasktypes;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The <code>TODO</code> type class
 * @see Task
 */
public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Gets the <code>TODO</code> information stored nicely in a <code>String[]</code> array
     * <p></p>
     * <p>
     * Used in the {@link duke.Storage} class for retrieving the <code>TODO</code> task's information to store it as
     * offline saved data
     * </p>
     * @return a <code>String[]</code> array containing the description, the time and whether the event is done
     * @see duke.Storage#save
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
        taskInfoArray[3] = "";
        return taskInfoArray;

    }
}
