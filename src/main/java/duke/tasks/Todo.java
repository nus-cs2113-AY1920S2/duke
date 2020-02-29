package duke.tasks;
/**
 * This is the sub class of the Task class in Duke
 * Each of this class follows the same values and
 * behaviours of a Task class.
 */
public class Todo extends Task{
    public Todo(String action) {
        super(action);
    }
    @Override
/**
 * This method prints the class in the following format:
 * [T][X] description
 */
    public String toString(){
        return "[T]" + super.toString();
    }
    @Override
    public String toFile(){
        return String.format("T" + STORAGE_DELIMITER + "%s", super.toFile());
    }

}