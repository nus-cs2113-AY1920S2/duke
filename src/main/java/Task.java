public class Task {
    protected String description;
    protected boolean isDone;
    protected int num;

    private static int curr = 1;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.num = curr;
        curr++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
