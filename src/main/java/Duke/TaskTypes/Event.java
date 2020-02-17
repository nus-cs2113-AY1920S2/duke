package Duke.TaskTypes;

/**
 * The <code>EVENT</code> type class
 */
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

    /**
     * Gets the <code>EVENT</code> information stored nicely in a <code>String[]</code> array
     * <p></p>
     * <p>
     *     Used in the {@link Duke.Storage} class for retrieving the <code>EVENT</code> task's information to store it as offline saved data
     * </p>
     * @return a <code>String[]</code> array containing the description, the time and whether the event is done
     * @see Duke.Storage#save
     */
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
