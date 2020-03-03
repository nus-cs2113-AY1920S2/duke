package duke.task;

import static duke.constant.Constant.*;

/**
 * Todo class extend from Task class to deal with Todo
 */
public class ToDo extends Task {
    /**
     * Todo constructor given a description from user
     *
     * @param description description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overriding of getType in Task
     * return first letter of Task
     *
     * @return "T"
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Override of string method in Task
     *
     * @return format of the task in output
     */
    @Override
    public String toString() {
        return TODO_TYPE + super.toString();
    }
}
