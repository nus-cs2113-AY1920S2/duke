public class Task {
    protected String description;
    protected boolean isDone;

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
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
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
