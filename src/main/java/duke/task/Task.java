package duke.task;

/**
 * Represents a task.
 */
public class Task {
    protected String name;
    protected String taskType;
    protected boolean isDone;

    public Task(){
        this("");
    }

    public Task(String name){
        setName(name);
        this.isDone = false;
    }

    /**
     * Set the name of task.
     * @param name the task name.
     */
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    /** Marks the task as done. */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Gets the Status icon of task.
     * @return status icon.
     */
    public String getStatusIcon(){
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public String getTaskType(){
        return null;
    }

    @Override
    public String toString(){
        return getStatusIcon() + " " + name;
    }

}
