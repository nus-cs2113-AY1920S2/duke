package duke.task;

public class Events extends Task {
    private final String dateTime;

    public Events(int taskId, String taskName, 
            String dateTime, boolean isDone) {

        super(taskId, taskName, isDone);
        this.dateTime = dateTime;
    }

    public Events(int taskId, String taskName, String dateTime) {
        super(taskId, taskName);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return this.dateTime;
    }
    
    @Override
    public Task setNewTaskId(int newTaskId) {
        return new Events(newTaskId, this.taskName, this.dateTime, this.isDone);
    }

    @Override
    public String taskWithSymbol() {
        return "[E]" 
                + ((this.isDone) ? Task.tickSymbol : Task.crossSymbol)
                + " " 
                + this.taskName;
    }

    @Override
    public Events makeDone() {
        return new Events(this.taskId, this.taskName, 
                this.dateTime, true);
    }

    @Override
    public String toString() {
        return this.taskId 
                + "."  
                + this.taskWithSymbol()
                + " (at: " + this.dateTime + ")";
    }
}
