package duke.task;

public class Task {
    protected final String taskName;
    protected final int taskId;
    protected boolean isDone;
    protected static final String tickSymbol = "[✓]";
    protected static final String crossSymbol = "[✗]";

    protected Task(int taskId, String taskName, boolean isDone) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.isDone = isDone;
    }

    protected Task(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.isDone = false;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }
    
    public Task setNewTaskId(int newTaskId) {
        return new Task(newTaskId, this.taskName, this.isDone);
    }

    public String taskWithSymbol() {
        return ((this.isDone) ? tickSymbol : crossSymbol)
                + "   " 
                + this.taskName;
    }

    public Task makeDone() {
        return new Task(this.taskId, this.taskName, true);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } 
        if (obj instanceof Task) {
            Task task = (Task) obj;
            return task.taskId == this.taskId;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.taskId + "." + this.taskWithSymbol(); 			
    }
}
