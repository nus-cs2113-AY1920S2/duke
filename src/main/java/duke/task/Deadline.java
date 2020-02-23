package duke.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description,String by) {
        super(description);
        this.by=by;
    }

    @Override
    public String toString(){
        String taskType="[D]";
        String detail = taskType + " [" + getStatusIcon() + "] " + description+" (by: "+by+" )";
        return detail;
    }
}
