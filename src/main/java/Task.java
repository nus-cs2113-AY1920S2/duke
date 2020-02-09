public class Task {
    private static int taskCount;

    protected char type;
    protected String description;
    protected boolean isDone;

    public Task(String s) {
        setDescription(s);
        setDone(false);
        setType('-');   // default type
    }

    public static void incrementTaskCount() {
        Task.taskCount += 1;
    }

    public static int getTaskCount() {
        return Task.taskCount;
    }

    public char getType() {
        return type;
    }

    protected void setType(char type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String s) {
        description = s;
    }

    public char getDone() {
        // return tick if done, cross if not done
        return isDone ? '\u2713' : '\u2717';
    }

    public void setDone(boolean status) {
        isDone = status;
    }

    @Override
    public String toString() {
        String output = "[" + getType() + "]" +
            "[" + getDone() + "] " +
            getDescription();
        return output;
    }
}