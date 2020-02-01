public class Task {

    // Stores the description of the task
    protected String description;
    // Denotes whether the task is done or not done
    protected boolean isDone;
    // Used to denote type of task
    protected char taskType;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Returns the status icon as a string
    // tick -> Task is done ;  X symbol -> Task isn't done ;
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    // Returns description of the task
    public String getDescription() {
        return description;
    }

    // Marks the task as done
    public void markAsDone(){
        isDone = true;
    }

    // Returns the task's status along with it's description as a string
    public String getStatusWithDescription(){
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public void printDetails() {
        PrintHelper.printWithIndentation("added: " + description );
    }
}
