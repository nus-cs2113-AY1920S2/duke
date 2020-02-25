package duke.task;

public class Task {

    public static final String SLASH = "/";
    public static final String SINGLE_SPACE = " ";

    // Stores the description of the task
    public String description;
    // Denotes whether the task is done or not done
    public boolean isDone;
    // Used to denote type of task
    public char taskType;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Returns the status icon as a string
    public String getStatusIcon() {
        return (isDone ? SLASH : SINGLE_SPACE);
    }

    // Returns description of the task
    public String getDescription() {
        return description;
    }

    // Set done as required
    public void setDone(boolean done) {
        isDone = done;
    }

    // Marks the task as done
    public void markAsDone() {
        isDone = true;
    }

    // Returns the task's status along with it's description as a string
    public String getStatusWithDescription() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public boolean hasKeyword(String Keyword) {
        return description.contains(Keyword);
    }

}
