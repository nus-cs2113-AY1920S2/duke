package duke.tasks;

public class Todo extends Task {

    /**
     * Defines the constructor.
     * Fills in the task content and specifies the type of the task as "T" Todo_task.
     * 
     * @param description Content of the task.
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Specifies the format when printing this task object.
     * 
     * @return Formatted string: [type][tick/cross] task content.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", this.type, super.toString());
    }
    
    /**
     * Specifies the format of string that will be written into the txt file.
     *
     * @return Formatted string: type | status icon | task content.
     */
    @Override
    public String getFileString() {
        return String.format("%s | %d | %s", type, isDone ? 1 : 0, description);
    }
}
