package duke.taskmanager;

public class Tasks {
    private static boolean isDone;
    public String task;

    public Tasks(String task) {
        this.task = task;
        isDone = false;
    }
    public void markAsDone() {
        isDone = true;
    }

    public String getTaskStatus() {
        return (isDone ? "Done" : "Not Done");
    }

    public String getTask() {
        return task;
    }

    public String contentToFile() {
        return task + "|" + (isDone? "Y" : "N");
    }

    @Override
    public String toString() {
        return "[" + getTaskStatus() + "]" + task;
    }
}
