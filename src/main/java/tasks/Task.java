package tasks;

/**
 * Also known as ToDos: tasks without any date/time attached to it
 * e.g., visit new theme park
  */
public class Task {
    protected String taskName;
    protected boolean isDone;
    protected char category;

    public Task(TaskType category, String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.category = 'T';
    }

    public char getCategory() {
        return category;
    }

    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markDone(){
        this.isDone = true;
    }


    /** Getters & Setters **/
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

    public String toString() {
        return ("[" +getCategory() +"][" + getStatusIcon() +"] " +getTaskName() );
    }
}
