public class Task {
    protected String description;
    protected boolean isDone;
    private static int listCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        listCount += 1;
    }

    public void updateIsDone (){
        this.isDone = true;
    }

    public String getDescription(){
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public static int getListCount() {
        return listCount;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}