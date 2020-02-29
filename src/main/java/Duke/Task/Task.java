package Duke.Task;

public class Task {
    
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public boolean isDone(){
        return isDone;
    }
    public void setDone(boolean isDone){
        this.isDone = isDone;
    }
    public String getTaskStatus() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }
    public String getDescription(){
        return description;
    }
    @Override
    public String toString() {
        return "[" + getTaskStatus() + "]" + description;
    }
    
}
