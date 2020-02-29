package data;

import java.util.ArrayList;

/**
 * This class represents the DEADLINE subtype of Tasks that can be stored in Duke. It contains a description and a deadline.
 */
public class Deadline extends Task {

    public static final int DEADLINE_NUMBER_OF_FIELDS = 4;
    public static final String DEADLINE_NOTATION = "D";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * This method converts the data for a DEADLINE object into a String array for easy parsing and application.
     * @return a String array consisting of the initial denoting the Task, its completion status and the description
     */
    @Override
    public void addIfContainsKeyword(ArrayList<Task> searchResults, String searchKeyword) {
        if (this.getDescription().contains(searchKeyword) || this.getBy().contains(searchKeyword)) {
            searchResults.add(this);
        }
    }

    @Override
    public String[] getTaskData(){
        String[] taskValues = new String[DEADLINE_NUMBER_OF_FIELDS];
        taskValues[0] = DEADLINE_NOTATION;
        if (this.getIsDone()) {
            taskValues[1] = TASK_DONE_NOTATION;
        } else {
            taskValues[1] = TASK_NOTDONE_NOTATION;
        }
        taskValues[2] = this.getDescription();
        taskValues[3] = this.getBy();
        return taskValues;
    }
}
