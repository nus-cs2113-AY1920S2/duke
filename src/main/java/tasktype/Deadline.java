package tasktype;

import java.util.ArrayList;

/**
 * This class represents the DEADLINE subtype of Tasks that can be stored in data.Duke. It contains a description and a deadline.
 */
public class Deadline extends Task {

    private static final int DEADLINE_NUMBER_OF_FIELDS = 4;
    private static final String DEADLINE_NOTATION = "D";
    private static final String DEADLINE_NOTATION_WITH_BRACKETS = "[D]";

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
        return DEADLINE_NOTATION_WITH_BRACKETS + super.toString() + " (by: " + by + ")";
    }

    /**
     * This method converts the data for a DEADLINE object into a String array for easy parsing and application.
     * @return a String array consisting of the initial denoting the Task, its completion status and the description
     */
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

    /**
     * This method adds the current Deadline object to a searchResult ArrayList if the Task's description or
     * field contains the keyword.
     * <p></p>
     * <p>This method is primarily used for the FIND command.</p>
     * @param searchResults a list of Tasks containing the search keyword
     * @param searchKeyword the keyword to be searched for in this Deadline object
     */
    @Override
    public void addIfContainsKeyword(ArrayList<Task> searchResults, String searchKeyword) {
        if (this.getDescription().contains(searchKeyword) || this.getBy().contains(searchKeyword)) {
            searchResults.add(this);
        }
    }
}
