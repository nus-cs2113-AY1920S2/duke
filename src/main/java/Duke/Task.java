package Duke;

/** Manages task operations */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description The description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task.
     * @param description The description of task.
     * @param isDone isDone is true if task is done, else false.
     */
    public Task(String description, Boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns symbols to denote if task is done or not.
     *
     * @return tick or X symbols.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks task as done if completed.
     */
    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toText(){
        return " ";
    }

}