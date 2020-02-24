package duke.task;

public abstract class Task {
    public String description;
    private boolean isDone;

    public Task(String description){
        this.description=description;
        this.isDone=false;
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
}
