public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount += 1;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }
    
    public void markAsDone() {
        this.isDone = true;
    }

    public static int getTaskCount() {
        return taskCount;
    }
    
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
    //Solution below adapted from https://www.baeldung.com/java-add-character-to-string
    protected String stringBuilder(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.insert(3,":");
        return sb.toString();
    }

}
