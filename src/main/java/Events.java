
public class Events extends Task {
    private final String dateTime;

    private Events(int taskId, String taskName, 
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
    public String taskWithSymbol() {
        return "[E]" 
                + ((this.isDone) ? "[✓]" : "[✗]")
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
