package task;

public class Task {
    protected String description;
    protected int index;
    protected String type;
    protected boolean isDone;

    public Task(String description, int index) {
        this.description = description;
        this.index = index;
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
        return (index+1) + ". " + type;
    }
}
