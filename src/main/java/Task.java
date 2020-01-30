public class Task {
    private static int taskCount;

    private String description;
    private boolean isDone;

    public Task(String s) {
        setDescription(s);
        setDone(false);
    }

    public char getDone() {
        return isDone ? '✓' : '✗';
    }

    public void setDone(boolean status) {
        isDone = status;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String s) {
        description = s;
    }

    @Override
    public String toString() {
        String output = "[" + getDone() + "] " + getDescription();
        return output;
    }
}