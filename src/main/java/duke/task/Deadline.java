package duke.task;

import duke.command.AddCommand;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description,String by) {
        super(description);
        this.by=by;
    }

    public Deadline(AddCommand addCommand){
        super(addCommand);
        this.by = addCommand.getTimeNotes();
    }

    @Override
    public String toString(){
        String taskType="[D]";
        String detail = taskType + " [" + getStatusIcon() + "] " + getDescription() +" (by: "+by+" )";
        return detail;
    }
}
