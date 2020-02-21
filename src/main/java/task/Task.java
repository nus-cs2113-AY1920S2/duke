package task;

public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }


    public final void setType(String type){
        this.type = type;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return ". " + type;
    }
}
