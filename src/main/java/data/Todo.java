package data;

/**
 * This class represents the TODO subtype of Tasks that can be stored in Duke. It contains a description.
 */
public class Todo extends Task {

    public static final int TODO_NUMBER_OF_FIELDS = 3;
    public static final String TODO_NOTATION = "T";
    protected String by;

    public Todo(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * This method converts the data for a TODO object into a String array for easy parsing and application.
     * @return a String array consisting of the initial denoting the Task, its completion status and the description
     */
    @Override
    public String[] getTaskData(){
        String[] taskValues = new String[TODO_NUMBER_OF_FIELDS];
        taskValues[0] = TODO_NOTATION;
        if (this.getIsDone()) {
            taskValues[1] = TASK_DONE_NOTATION;
        } else {
            taskValues[1] = TASK_NOTDONE_NOTATION;
        }
        taskValues[2] = this.getDescription();
        return taskValues;
    }
}
