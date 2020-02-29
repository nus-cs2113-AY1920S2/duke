package data;

import java.util.ArrayList;

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
