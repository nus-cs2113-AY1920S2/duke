package data;

public class Task {

    //protected int index;
    protected String taskDescription;
    protected boolean isDone;

    public Task() {
    }

    public Task(String taskDescription) {

        this.taskDescription = taskDescription;
        isDone = false;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public char getChar(){
        char isDone;
        if (this.isDone){
            return '✓';
        } else {
            return '✗';
        }
    }
    /**
     * Returns true if both persons have the same identity fields (name and telephone).
     */
    boolean isSamePerson(Task other) {
        return (other == this)
                || (other != null
                && other.getTaskDescription().equals(this.getTaskDescription()));
    }
}
