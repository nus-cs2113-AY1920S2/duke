package duke.task;

import duke.command.AddCommand;

/**
 * Task is an abstract type.
 * This stipulates that every kind of task should have two basic information:
 * description about the task and the state of the task.
 */
public abstract class Task {
    protected String category;
    private String description;
    private boolean isDone;

    /**
     * A constructor that creates a task with the description.
     * And assume the task is not done at the time creating it.
     *
     * @param description A description about the task.
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task according to a command.
     *
     * @param addCommand A command which includes the description of the task to add.
     */
    public Task(AddCommand addCommand){
        this.description = addCommand.getDescription();
        this.isDone = false;
    }

    /**
     * Returns an icon according to the different status of a task.
     *
     * @return An icon represents the status of the task.
     */
    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone(){
        this.isDone=true;
    }

    /**
     * Returns a string contains all the information of a todo task.
     *
     * @return A string includes type, status and description of the todo task.
     */
    @Override
    abstract public String toString();

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
