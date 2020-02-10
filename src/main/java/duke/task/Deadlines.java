package duke.task;

public class Deadlines extends Task {
    private final String dateTime;

    private Deadlines(int taskId, String taskName, 
            String dateTime, boolean isDone) {

        super(taskId, taskName, isDone);
        this.dateTime = dateTime;
    }

    public Deadlines(int taskId, String taskName, String dateTime) {
        super(taskId, taskName);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return this.dateTime;
    }
    
    @Override
    public Task setNewTaskId(int newTaskId) {
        return new Deadlines(newTaskId, this.taskName, this.dateTime, this.isDone);
    }

    @Override
    public String taskWithSymbol() {
        return "[D]" 
                + ((this.isDone) ? Task.tickSymbol : Task.crossSymbol)
                + " " 
                + this.taskName;
    }

    @Override
    public Deadlines makeDone() {
        return new Deadlines(this.taskId, this.taskName, 
                this.dateTime, true);
    }

    @Override
    public String toString() {
        return this.taskId 
                + "." 
                + this.taskWithSymbol()
                + " (by: " + this.dateTime + ")";
    }
}
