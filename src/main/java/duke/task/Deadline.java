package duke.task;

import duke.command.AddCommand;

/**
 * Deadline is a subtype of task which have a deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * A constructor creates a new deadline with description and a deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description,String by) {
        super(description);
        this.by=by;
    }

    /**
     * A constructor creates a new deadline according to a command.
     *
     * @param addCommand A command includes description and a deadline of a task.
     */
    public Deadline(AddCommand addCommand){
        super(addCommand);
        this.by = addCommand.getTimeNotes();
    }

    /**
     * Returns a string contains all the information of an deadline task.
     *
     * @return A string includes type, status ,description and deadline of the deadline task.
     */
    @Override
    public String toString(){
        String taskType="[D]";
        String detail = taskType + " [" + getStatusIcon() + "] " + description+" (by: "+by+" )";
        return detail;
    }
}
