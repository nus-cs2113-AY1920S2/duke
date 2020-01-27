public class Task {
    protected int taskID;
    protected String taskName;
    protected boolean isDone;

    public Task(int taskID, String taskName) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markDone(int taskID){
        this.isDone = true;
    }


    /** Getters & Setters **/
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }
}
