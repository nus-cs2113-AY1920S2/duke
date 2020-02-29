package duke.task;

import duke.command.AddCommand;

public abstract class Task {

    protected String category;
    private String description;
    private boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public Task(AddCommand addCommand){
        this.description = addCommand.getDescription();
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone(){
        this.isDone=true;
    }

    @Override
    abstract public String toString();

    public void setDone() {
        isDone = true;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
