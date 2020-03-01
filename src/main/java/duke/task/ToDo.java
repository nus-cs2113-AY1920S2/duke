package duke.task;

import duke.command.AddCommand;

/**
 *  ToDo represents a task which is going to do without a specific time or deadline.
 *  ToDo corresponds to a task which only have a description about it.
 */
public class ToDo extends Task{

    /**
     * A constructor that create a new todo task with a description.
     *
     * @param description The description about the task to do.
     */
    public ToDo(String description) {
        super(description);
        this.category="todo";
    }

    /**
     * A constructor that create a new todo task according a command.
     *
     * @param addCommand A command includes the description of the new todo task.
     */
    public ToDo(AddCommand addCommand){
        super(addCommand);
        this.category="todo";
    }

    @Override
    public String toString(){
        String taskType="[T]";
        String detail = taskType + " [" + getStatusIcon() + "] " + getDescription();
        return detail;
    }
}
