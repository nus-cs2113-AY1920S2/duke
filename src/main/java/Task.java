public class Task {

    private String name;
    private int taskNumber;
    private boolean isDone;

    public Task(String name, int taskNumber) {
        this.name = name;
        this.taskNumber = taskNumber;
        isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getName() {
        return name;
    }

    public void printTask() {
        System.out.println("    " + taskNumber + ". [" + getStatusIcon() + "] " + name);
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

}
