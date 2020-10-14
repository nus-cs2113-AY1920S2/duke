package Duke.data.objects;

public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Represents the Task class. Parent class of Deadline, Event, ToDo.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markAsDone(){
        this.isDone = true;
    }
    public String getDescription(){
        return this.description;
    }
    public boolean getIsDone(){
        return this.isDone;
    }
    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "Done" : "Not Done");
    }
    @Override
    public String toString(){
        return "["+getStatusIcon() +"]" +" "+ this.description;
    }
    public String getType(){
        return "";
    }
    public String getExtra(){
        return "";
    }
}
